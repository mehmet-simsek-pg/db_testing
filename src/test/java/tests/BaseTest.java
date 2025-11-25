package tests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    private Properties getProperties() {

        Properties properties = new Properties();
        try {
            // Sistemdeki hangi dosyadan okuma yapacagini belirtiyoruz
            InputStream inputStream =
                    getClass().getClassLoader().getResourceAsStream("db.properties");

            if (inputStream == null) { // Dosyanin olup olmadigini kontrol ediyoruz
                throw new RuntimeException("db.properties dosyasi bulunamadi");
            } else {
                properties.load(inputStream); // dosya var ise icerigini okutuyoruz
            }
        } catch (IOException e) {
            throw new RuntimeException("Dosya yüklenirken hata olustu");
        }
        return properties;
    }
}
