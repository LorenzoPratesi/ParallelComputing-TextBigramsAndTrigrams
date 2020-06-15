package lorenzopratesi.benchmarks;

import lorenzopratesi.parallel.ParallelComputing;
import lorenzopratesi.utils.Utils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
//@Fork(value = 1)
//@Warmup(iterations = 1)
//@Measurement(iterations = 1)

public class ParallelComputingBenchmark {

    public static void main(String[] args) throws RunnerException {
        final String folderPath = System.getProperty("user.dir") + "/src/main/java/lorenzopratesi/benchmarks/results/trigrams/";

        Options opt = new OptionsBuilder()
                .include(ParallelComputingBenchmark.class.getSimpleName())
//                .resultFormat(ResultFormatType.CSV)
//                .result(folderPath + "parallel" + ".csv")
                .forks(1)
                .build();

        new Runner(opt).run();
    }

//    @Benchmark
//    public void parallel2Grams(ExecutionPlan plan) throws ExecutionException, InterruptedException {
//        plan.parallelComputing.parallelWorks(plan.DATA_FOR_TESTING, 2, plan.numOfThreads);
//    }

    @Benchmark
    public void parallel3Grams(ExecutionPlan plan) throws ExecutionException, InterruptedException {
        plan.parallelComputing.parallelWorks(plan.DATA_FOR_TESTING, 3, plan.numOfThreads);
    }


    @State(Scope.Thread)
    public static class ExecutionPlan {

        @Param({"2", "4", "6", "8", "10", "12"})
        private int numOfThreads;

        @Param({"1-text50KB", "2-text500KB", "3-text10MB", "4-text50MB", "5-text100MB", "6-text150MB"})
        private String files;

        private List<Character> DATA_FOR_TESTING;

        private ParallelComputing parallelComputing;

        @Setup(Level.Invocation)
        public void setUp() throws IOException {
            DATA_FOR_TESTING = Utils.readTextFromFile(this.files + ".txt");
            this.parallelComputing = new ParallelComputing();
        }
    }
}
