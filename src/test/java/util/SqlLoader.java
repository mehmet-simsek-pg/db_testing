package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SqlLoader {

    // Sql dosyalarindan veri okumak icin bu metodu yazdik
    public static String loadSql(String path) {
        ClassLoader classLoader = SqlLoader.class.getClassLoader();

        try (InputStream is = classLoader.getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("SQL file not found: " + path);
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8))) {

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }

                return sb.toString();
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading SQL file: " + path, e);
        }
    }
}
