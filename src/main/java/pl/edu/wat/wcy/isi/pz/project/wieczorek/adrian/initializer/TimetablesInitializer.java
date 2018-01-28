package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.initializer;

import org.xml.sax.SAXException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.ConfigManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.DatabaseManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.exception.NotFoundXMLException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TimetablesInitializer {
    public static void init(){
        Runnable runnable = () -> {
            if(Boolean.parseBoolean(ConfigManager.getInstance().getProperty("initializeDB"))){
                try {
                    DatabaseManager databaseManager = DatabaseManager.getInstance();
                    databaseManager.loadDictionaries();
                    databaseManager.loadExampleData();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (NotFoundXMLException e) {
                    e.printStackTrace();
                }
            }
        };

        runnable.run();
    }
}
