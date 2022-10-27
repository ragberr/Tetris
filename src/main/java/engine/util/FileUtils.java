package engine.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static String loadAsString(String path) {
        StringBuilder result = new StringBuilder();
        try {
            List<String> list = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
            for(String line : list) {
                result.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Couldn't find the file at " + path);
        }
        return result.toString();
    }
}
