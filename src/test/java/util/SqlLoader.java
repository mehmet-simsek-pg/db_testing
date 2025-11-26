package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SqlLoader {

    /**
     * Resource klasöründen SQL dosyasını okuyup String olarak döner.
     * @param path Ör: "sql/departments/select.sql"
     */
    public static String loadSql(String path) {

        // 1) ClassLoader oluştur – Java'nın resource dosyalarını bulmak için kullandığı mekanizma.
        ClassLoader classLoader = SqlLoader.class.getClassLoader();

        // 2) Verilen path'e göre resource’u InputStream olarak açmaya çalış.
        try (InputStream is = classLoader.getResourceAsStream(path)) {

            // 3) Eğer dosya bulunamazsa is null olur – bu durumda hata fırlatıyoruz.
            if (is == null) {
                throw new RuntimeException("SQL file not found: " + path);
            }

            // 4) Dosyanın içeriğini satır satır okumak için BufferedReader oluşturuyoruz.
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8))) {

                // 5) Okunan tüm satırları biriktirmek için StringBuilder kullanıyoruz.
                StringBuilder sb = new StringBuilder();
                String line;

                // 6) Reader’dan satır satır oku. Satır null olursa dosyanın sonuna geldik demektir.
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n"); // 7) Okunan her satırı sonuna \n ekleyerek StringBuilder’a ekle.
                }

                // 8) Tüm SQL içeriğini tek bir String olarak döndür.
                return sb.toString();
            }

        } catch (IOException e) {
            // 9) Reader veya InputStream sırasında bir hata olursa buraya düşeriz.
            throw new RuntimeException("Error reading SQL file: " + path, e);
        }
    }
}