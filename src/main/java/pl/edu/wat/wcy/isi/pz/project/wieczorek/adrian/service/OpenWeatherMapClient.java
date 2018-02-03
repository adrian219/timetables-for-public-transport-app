package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.ConfigManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.exception.NotInternetConnectionException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.service.pojo.WeatherForecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.stream.Collectors;

public class OpenWeatherMapClient {
    private static final String OPEN_WEATHER_MAP_API_KEY = ConfigManager.getInstance().getProperty("openWeatherMapApiKey");
    private static final String OPEN_WEATHER_MAP_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=<CITY_NAME>&APPID=" + OPEN_WEATHER_MAP_API_KEY;
    public static final String OPEN_WEATHER_MAP_ICON_URL = "http://openweathermap.org/img/w/<ICON_ID>.png";

    Logger log = LoggerFactory.getLogger(OpenWeatherMapClient.class);

    public WeatherForecast getWeatherForecast(String cityName) throws IOException, NotInternetConnectionException {
        String urlAddress = OPEN_WEATHER_MAP_API_URL.replace("<CITY_NAME>", cityName);
        URL url = new URL(urlAddress);
        InputStreamReader reader;
        try{
            reader = new InputStreamReader(url.openStream());
        }catch(UnknownHostException e){
            log.info(e.getMessage());
            throw new NotInternetConnectionException();
        }

        BufferedReader bufferedReader = new BufferedReader(reader);

        String response = bufferedReader.lines().collect(Collectors.joining());
        log.info(response);
        Gson g = new Gson();
        return g.fromJson(response, WeatherForecast.class);
    }

    public String getURLWeatherForecastIcon(String cityName) throws IOException, NotInternetConnectionException {
        WeatherForecast weatherForecast = getWeatherForecast(cityName);

        return OPEN_WEATHER_MAP_ICON_URL.replace("<ICON_ID>", weatherForecast.getWeather()[0].getIcon());
    }
}
