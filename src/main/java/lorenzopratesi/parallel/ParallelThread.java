package lorenzopratesi.parallel;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class ParallelThread implements Callable<ConcurrentHashMap<String, Integer>> {

    private int n;
    private double start, stop;
    private ConcurrentHashMap<String, Integer> grams;
    private List<Character> fileString;

    private StringBuilder builder;

    ParallelThread(double start, double stop, int n, List<Character> fileString) {   //n is the n-grams dimension
        this.start = start;
        this.stop = stop;
        this.n = n;
        this.fileString = fileString;
        this.grams = new ConcurrentHashMap<>();
    }

    public ConcurrentHashMap<String, Integer> call() {
        if (stop > fileString.size() - 1) {
            stop = fileString.size() - 1;
        }

        for (double i = this.start + n - 1; i <= this.stop; i++) {
            builder = new StringBuilder();

            for (double j = n - 1; j >= 0; j--) {
                builder.append(this.fileString.get((int) (i - j)));
            }

            if (this.n == 2 || this.n == 3) {

                String key = builder.toString();

                if (!this.grams.containsKey(key)) {
                    this.grams.put(builder.toString(), 1);
                } else {
                    if (this.grams.containsKey(key)) {
                        this.grams.put(builder.toString(), this.grams.get(key) + 1);
                    }
                }
            } else {
                System.out.println("invalid n");
            }
        }
        return grams;
    }
}
