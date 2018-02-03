package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.service;

import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.ConfigManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.exception.NotFoundHTMLException;

import java.net.URL;

public class GoogleMapClient {
    private static final String URL_ADDRESS = "htmls/mapGoogle.html";
    private static final String API_KEY_GOOGLE = ConfigManager.getInstance().getProperty("googleApiKey");

    public URL getURLMap() throws NotFoundHTMLException {
        URL url = getClass().getClassLoader().getResource(URL_ADDRESS);

        if(url == null){
            throw new NotFoundHTMLException();
        }

        return url;
    }
}
