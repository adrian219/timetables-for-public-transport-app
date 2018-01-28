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

    private ConfigManager() throws IOException{
        properties = new Properties();
        loadProperties();
    }

    public static ConfigManager getInstance() throws IOException{
        if(configManager == null){
            configManager = new ConfigManager();
        }
        return configManager;
    }

    private void loadProperties() throws IOException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResource(CONFIG_FILENAME).openStream();
        properties.load(inputStream);
    }

    public String getProperty(String key) throws IOException {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) throws IOException {
        properties.setProperty(key, value);
    }
}
