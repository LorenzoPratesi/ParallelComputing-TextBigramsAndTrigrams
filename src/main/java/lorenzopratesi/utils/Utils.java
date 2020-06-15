package lorenzopratesi.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static List<Character> readTextFromFile(String fileName) {
        final String filePath = System.getProperty("user.dir") + "/src/main/resources/" + fileName;

        try (Stream<String> fileStream = Files.lines(Paths.get(filePath))) {
            return fileStream.flatMap(Utils::trimAndMapToChars)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Stream<Character> trimAndMapToChars(String s) {
        return s.toLowerCase()
                .replaceAll("[^a-zA-Z]", "")
                .chars()
                .mapToObj(c -> (char) c);
    }

    public static void printSet(String key, Integer value) {
        System.out.println("ngram: " + key + ", value: " + value);
    }
}
