package lorenzopratesi.sequential;

import lorenzopratesi.main.TextBigramsAndTrigrams;
import lorenzopratesi.utils.Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SequentialComputing {
    private String fileName;
    private int numberOfGrams;   // set n_grams

    public SequentialComputing() {
    }

    public SequentialComputing(String fileName, int numberOfGrams) {
        this.fileName = fileName;
        this.numberOfGrams = numberOfGrams;
    }

    public Map<String, Integer> computeNGrams(List<Character> fileString, int numberOfGrams) {
        Map<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < fileString.size() - numberOfGrams + 1; ++i) {
            StringBuilder builder = new StringBuilder();

            for (int j = 0; j < numberOfGrams; ++j) {
                builder.append(fileString.get(i + j));
            }

            String key = builder.toString();

            if (!hashMap.containsKey(key)) {
                hashMap.put(builder.toString(), 1);
            } else if (hashMap.containsKey(key)) {
                hashMap.put(builder.toString(), hashMap.get(key) + 1);
            }
        }
        return hashMap;
    }

    public void run() throws IOException {
        List<Character> fileString = Utils.readTextFromFile(this.fileName);
        long start, end;

        start = System.currentTimeMillis();
        Map<String, Integer> map = computeNGrams(fileString, this.numberOfGrams);
        end = System.currentTimeMillis();

        if (TextBigramsAndTrigrams.printExecution) {
            map.forEach(Utils::printSet);
        }

        System.out.println(end - start);
    }
}
