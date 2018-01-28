package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.service;

import com.google.gson.Gson;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.ConfigManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.service.pojo.WeatherForecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class OpenWeatherMapClient {
    private static final String OPEN_WEATHER_MAP_API_KEY = ConfigManager.getInstance().getProperty("openWeatherMapApiKey");
    private static final String OPEN_WEATHER_MAP_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=<CITY_NAME>&APPID=" + OPEN_WEATHER_MAP_API_KEY;
    public static final String OPEN_WEATHER_MAP_ICON_URL = "http://openweathermap.org/img/w/<ICON_ID>.png";

    public WeatherForecast getWeatherForecast(String cityName) throws IOException {
        String urlAddress = OPEN_WEATHER_MAP_API_URL.replace("<CITY_NAME>", cityName);
        URL url = new URL(urlAddress);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        BufferedReader bufferedReader = new BufferedReader(reader);

        String response = bufferedReader.lines().collect(Collectors.joining());
        System.out.println(response);
        Gson g = new Gson();
        return g.fromJson(response, WeatherForecast.class);
    }

    public String getURLWeatherForecastIcon(String cityName) throws IOException {
        WeatherForecast weatherForecast = getWeatherForecast(cityName);

        return OPEN_WEATHER_MAP_ICON_URL.replace("<ICON_ID>", weatherForecast.getWeather()[0].getIcon());
    }
}
