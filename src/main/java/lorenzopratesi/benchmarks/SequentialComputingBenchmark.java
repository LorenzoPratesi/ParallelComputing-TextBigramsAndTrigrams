package lorenzopratesi.benchmarks;

import lorenzopratesi.utils.Utils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import lorenzopratesi.sequential.SequentialComputing;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
//@Fork(value = 1)
//@Warmup(iterations = 1)
//@Measurement(iterations = 1)

public class SequentialComputingBenchmark {

    public static void main(String[] args) throws RunnerException {
        final String folderPath = System.getProperty("user.dir") + "/src/main/java/lorenzopratesi/benchmarks/results/trigrams/";

        Options opt = new OptionsBuilder()
                .include(SequentialComputingBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .result(folderPath + "sequential" + ".csv")
                .forks(1)
                .build();

        new Runner(opt).run();
    }

//    @org.openjdk.jmh.annotations.Benchmark
//    public void sequential2Grams(ExecutionPlan plan) {
//        plan.sequentialComputing.computeNGrams(plan.DATA_FOR_TESTING, 2);
//    }

    @org.openjdk.jmh.annotations.Benchmark
    public void sequential3Grams(ExecutionPlan plan) {
        plan.sequentialComputing.computeNGrams(plan.DATA_FOR_TESTING, 3);
    }

    @State(Scope.Thread)
    public static class ExecutionPlan {
        private List<Character> DATA_FOR_TESTING;

        private SequentialComputing sequentialComputing;

        @Param({"1-text50KB", "2-text500KB", "3-text10MB", "4-text50MB", "5-text100MB", "6-text150MB"})
        private String files;

        @Setup(Level.Invocation)
        public void setUp() throws IOException {
            DATA_FOR_TESTING = Utils.readTextFromFile(this.files + ".txt");
            this.sequentialComputing = new SequentialComputing();
        }
    }
}
