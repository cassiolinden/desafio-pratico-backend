package core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Carrega configurações do arquivo config.properties em test resources e permite
 * sobreposição por propriedades de sistema (-Dkey=value) no Maven/CI.
 */
public class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            throw new RuntimeException("Falha ao carregar config.properties", e);
        }
    }

    private static String sysOrFile(String key) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) return sys;
        return props.getProperty(key);
    }

    public static String baseURI() {
        return sysOrFile("restassured.baseURI");
    }

}
