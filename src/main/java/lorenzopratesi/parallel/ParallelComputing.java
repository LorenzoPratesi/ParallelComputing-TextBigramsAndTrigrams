package lorenzopratesi.parallel;

import lorenzopratesi.main.TextBigramsAndTrigrams;
import lorenzopratesi.utils.Utils;

import java.util.List;
import java.util.concurrent.*;

import static java.util.stream.Collectors.*;

import java.util.stream.IntStream;

public class ParallelComputing {
    private String fileName;
    private int numberOfGrams;   // set n_grams
    private int numberOfThreads;  // set threads number

    public ParallelComputing() {
    }

    public ParallelComputing(String fileName, int numberOfGrams, int numberOfThreads) {
        this.fileName = fileName;
        this.numberOfGrams = numberOfGrams;
        this.numberOfThreads = numberOfThreads;
    }

    private void hashMerge(ConcurrentMap<String, Integer> finalGrams, ConcurrentMap<String, Integer> nGrams) {
        nGrams.forEach((k, v) -> finalGrams.merge(k, v, Integer::sum));
    }

    public void run() throws InterruptedException, ExecutionException {
        List<Character> fileString = Utils.readTextFromFile(this.fileName);

        long start, end;
        if (fileString != null) {
            start = System.currentTimeMillis();

            ConcurrentMap<String, Integer> finalGrams = parallelWorks(fileString, this.numberOfGrams, this.numberOfThreads);

            end = System.currentTimeMillis();

            if (TextBigramsAndTrigrams.printExecution) {
                finalGrams.forEach(Utils::printSet);
            }

            System.out.println("\nn-gram calculated in " + (end - start) + " ms");
        }
    }

    public ConcurrentMap<String, Integer> parallelWorks(List<Character> fileString, int numberOfGrams, int numberOfThreads) throws InterruptedException, ExecutionException {
        ConcurrentMap<String, Integer> finalGrams = new ConcurrentHashMap<>();
        List<Future<ConcurrentHashMap<String, Integer>>> futuresList;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        CompletionService<ConcurrentHashMap<String, Integer>> completionService = new ExecutorCompletionService<>(executor);

        double fileLen = fileString.size();
        double k = Math.floor(fileLen / numberOfThreads);
        futuresList = IntStream.range(0, numberOfThreads)
                .mapToObj(i -> completionService.submit(instantiateThreads(fileString, numberOfGrams, k, i)))
                .collect(toList());

        for (Future<ConcurrentHashMap<String, Integer>> future : futuresList) {
            hashMerge(finalGrams, future.get());
        }

        awaitTerminationAfterShutdown(executor);
        return finalGrams;
    }

    private ParallelThread instantiateThreads(List<Character> fileString, int numberOfGrams, double k, int i) {
        return new ParallelThread(i * k, ((i + 1) * k) + (numberOfGrams - 1) - 1, numberOfGrams, fileString);
    }

    private void awaitTerminationAfterShutdown(ExecutorService threadPool) throws InterruptedException {
        threadPool.shutdown();
        if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
            threadPool.shutdownNow();
        }
    }
}

