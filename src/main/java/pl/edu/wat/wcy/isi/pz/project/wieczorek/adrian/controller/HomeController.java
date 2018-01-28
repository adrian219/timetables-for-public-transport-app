package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.joda.time.DateTime;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.ConfigManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.DatabaseManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.*;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.enums.Language;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.enums.Style;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.provider.I18nProvider;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.service.OpenWeatherMapClient;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.service.pojo.WeatherForecast;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.style.AppStyle;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view.HomeView;

import java.io.IOException;
import java.util.*;

/**
 * Created by Adrian Wieczorek on 12/2/2017.
 */
public class HomeController extends Controller<HomeView> {
    private WeatherForecast weatherForecast;
    private LineData chooseLine;
    private StopData chooseStop;

    public HomeController(HomeView view) throws IOException {
        super(view);
        configureController();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        DatabaseManager dbm = DatabaseManager.getInstance();

        StringBuilder builder = new StringBuilder();

        List<TimetableData> list = dbm.getTimetables(dbm.getAllObjectList(StopData.class).get(0), dbm.getAllObjectList(LineData.class).get(0));
        for(TimetableData timetableData : list){
            builder.append(timetableData.getDepartureTime().toString() + " " + timetableData.getLineData().getNumberLine() + "\n");
        }

        alert.setContentText(builder.toString());

        alert.show();
    }

    private void configureController() throws IOException {
        updateWeather(ConfigManager.getInstance().getProperty("city"));
        setTexts();
        setActionOnClickTestButton();
        setActionOnClickSearchByStopButton();
        setActionOnClickSearchByLineButton();
        setActionOnClickShowCityMapButton();
        setActionOnClickLanguageMenuItem();
        setActionOnClickAppStyleMenuItem();
    }

    public void setTexts() {
        I18nProvider i18nProvider = I18nProvider.getInstance();

        getView().getFileMenu().setText(i18nProvider.getText("fileMenu"));
        getView().getAboutMenu().setText(i18nProvider.getText("aboutMenu"));
        getView().getCloseItem().setText(i18nProvider.getText("closeItem"));
        getView().getAboutItem().setText(i18nProvider.getText("versionItem"));

        //dynamic menus
        for (Map.Entry<String, Menu> entry : getView().getMenuMap().entrySet()) {
            entry.getValue().setText(i18nProvider.getText(entry.getKey()));
        }

        //dynamic menu items
        for (Map.Entry<String, HashMap<String, MenuItem>> entry : getView().getMenuItemsMap().entrySet()) {
            for (Map.Entry<String, MenuItem> entry1 : entry.getValue().entrySet()) {
                entry1.getValue().setText(i18nProvider.getText(entry1.getKey() + "MenuItem"));
            }
        }

        getView().getInfoLabel().setText(i18nProvider.getText("infoLabel"));
        getView().getSearchByLineButton().setText(i18nProvider.getText("searchByLineButton"));
        getView().getSearchByStopButton().setText(i18nProvider.getText("searchByStopButton"));
        getView().getShowCityMapButton().setText(i18nProvider.getText("showCityMapButton"));
        getView().getButton().setText(i18nProvider.getText("buttonTest"));
        setLabelWeather();
    }

    private void setLabelWeather() {
        getView().getWeatherLabel().setText(weatherForecast.getMain().getTempInCelsius() + " °C, " + I18nProvider.getInstance().getText("cityName"));
    }

    private void updateWeather(String cityName) throws IOException {
        OpenWeatherMapClient client = new OpenWeatherMapClient();
        weatherForecast = client.getWeatherForecast(cityName);
        getView().getWeatherImage().setImage(new Image(weatherForecast.getURLIcon()));
        setLabelWeather();
    }

    private void setActionOnClickTestButton() {
        getView().getButton().setOnAction(e -> {
                    getView().getTextArea().setText(getView().getTextArea().getText() + DateTime.now().toString() + ": Awaria komputera" + "\n");
                    showAlert();
                }
        );
    }

    private void setActionOnClickSearchByLineButton() {
        getView().getSearchByLineButton().setOnAction(e -> {
            //getModel().downloadLineData();
        });
    }

    private void setActionOnClickSearchByStopButton() {
        getView().getSearchByStopButton().setOnAction(e -> {
            //getModel().downloadStopData();
        });
    }

    private void setActionOnClickShowCityMapButton() {
        getView().getShowCityMapButton().setOnAction(e -> {
            //getModel().downloadCityMap();
        });
    }

    private void setActionOnClickLanguageMenuItem() {
        HashMap<String, MenuItem> hashMap = getView().getMenuItemsMap().get(Language.class.getSimpleName());
        for (Map.Entry<String, MenuItem> entry : hashMap.entrySet()) {
            System.out.println("entry: " + entry.getKey());
            entry.getValue().setOnAction(e -> {
                I18nProvider.getInstance().changeLanguage(Language.valueOf(entry.getKey().toUpperCase()).asLocale());
                setTexts();
            });
        }
    }

    private void setActionOnClickAppStyleMenuItem() {
        HashMap<String, MenuItem> hashMap = getView().getMenuItemsMap().get(Style.class.getSimpleName());
        for (Map.Entry<String, MenuItem> entry : hashMap.entrySet()) {
            System.out.println("entry style: " + entry.getKey());
            entry.getValue().setOnAction(e -> AppStyle.getInstance().setStyle(getView(), Style.valueOf(entry.getKey().toUpperCase())));
        }
    }

}
