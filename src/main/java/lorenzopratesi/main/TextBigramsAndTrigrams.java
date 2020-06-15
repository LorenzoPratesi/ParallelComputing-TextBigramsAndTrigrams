package lorenzopratesi.main;

import lorenzopratesi.parallel.ParallelComputing;
import lorenzopratesi.sequential.SequentialComputing;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TextBigramsAndTrigrams {

    public static boolean printExecution = false;

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        if (args.length < 3 || args.length > 4) {
            helper();
            return;
        }

        final String fileName = args[0];
        final String processingType = args[1];
        final int numberOfGrams = Integer.parseInt(args[2]);

        if (processingType.contains("print")) {
            printExecution = true;
        }

        if (processingType.equals("-p") || processingType.equals("-pprint")) {
            final int numberOfThreads = Integer.parseInt(args[3]);
            ParallelComputing parallelComputing = new ParallelComputing(fileName, numberOfGrams, numberOfThreads);
            parallelComputing.run();

        } else if (processingType.equals("-s") || processingType.equals("-sprint")) {
            SequentialComputing sequentialComputing = new SequentialComputing(fileName, numberOfGrams);
            sequentialComputing.run();
        }
    }

    private static void helper() {
        String options = "Options description: \n\t" +
                "[-p] stand for parallel computing\n\t" +
                "[-s] stand for lorenzopratesi.sequential computing\n\t" +
                "[-pprint] stand for parallel computing with execution print\n\t" +
                "[-sprint] stand for lorenzopratesi.sequential computing with execution print";
        String usage = "Usage: \n\t " +
                "java Main <name> -option <argument> [<optional-argument>]";
        String example = "Example: \n\t" +
                "java Main text.txt -p 3 8\n\t" +
                "java Main text.txt -s 3\n\t" +
                "java Main text.txt -s 2";

        System.out.println(options);
        System.out.println(usage);
        System.out.println(example);
    }
}
