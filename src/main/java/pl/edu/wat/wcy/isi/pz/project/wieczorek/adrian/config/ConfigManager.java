package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config;

import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.provider.I18nProvider;
import sun.security.krb5.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Adrian Wieczorek on 12/2/2017.
 */
public class ConfigManager {
    private static String CONFIG_FILENAME = "config.properties";
    private static ConfigManager configManager;
    private Properties properties;

    private ConfigManager() {
        properties = new Properties();
        loadProperties();
    }

    public static ConfigManager getInstance() {
        if(configManager == null){
            configManager = new ConfigManager();
        }
        return configManager;
    }

    private void loadProperties() {
        InputStream inputStream;
        try {
            inputStream = Thread.currentThread().getContextClassLoader().getResource(CONFIG_FILENAME).openStream();
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}
