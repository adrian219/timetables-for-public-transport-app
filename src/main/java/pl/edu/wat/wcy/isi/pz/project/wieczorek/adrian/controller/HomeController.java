package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.component.LineButton;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.ConfigManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.DatabaseManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.*;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.enums.Language;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.enums.Style;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.exception.NotFoundHTMLException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.exception.NotInternetConnectionException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.exception.NotTimetableForDirectionStopException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.exception.UpdateWeatherException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.helper.TimetableHelper;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.provider.I18nProvider;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.service.GoogleMapClient;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.service.OpenWeatherMapClient;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.service.pojo.WeatherForecast;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.style.AppStyle;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view.HomeView;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view.util.TimetableElement;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by Adrian Wieczorek on 12/2/2017.
 */
public class HomeController extends Controller<HomeView> {
    private WeatherForecast weatherForecast;
    private Long selectedLineDataId;
    private Long selectedStopDataId;
    private String selectedDay = "week";
    private Integer changerDirection = 1;

    private Logger log = LoggerFactory.getLogger(HomeController.class);

    public HomeController(HomeView view) throws IOException {
        super(view);
        configureController();
    }

    private void configureController() throws IOException {
        log.info("Configure controller");
        try {
            updateWeather(ConfigManager.getInstance().getProperty("city"));
        } catch (UpdateWeatherException e) {
            log.info("Cannot refresh weather!");
            log.info(e.getMessage());
        }
        setTexts();
        setActionOnClickSearchByStopButton();
        setActionOnClickSearchByLineButton();
        setActionOnClickShowCityMapButton();
        setActionOnClickLanguageMenuItem();
        setActionOnClickAppStyleMenuItem();
        setFirstStepActionOnDoubleClickOnElementStopListView();
        setFirstStepActionOnClickLineButton();
        setActionOnClickChangeDirectionButton();
        setActionOnClickRefreshWeatherButton();
        setGoogleMapWebViewProperty();
        setOnActionOnClickWeekButton();
        setOnActionOnClickWeekendButton();
    }

    public void setTexts() {
        log.info("Set texts in app");

        I18nProvider i18nProvider = I18nProvider.getInstance();

        log.info("creating dynamic menus");
        //dynamic menus
        for (Map.Entry<String, Menu> entry : getView().getMenuMap().entrySet()) {
            entry.getValue().setText(i18nProvider.getText(entry.getKey()));
        }

        log.info("creating menu items to menus");
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
        getView().getHourColumn().setText(i18nProvider.getText("hourColumn"));
        getView().getMinuteColumn().setText(i18nProvider.getText("minuteColumn"));
        getView().getInformationLabel().setText(i18nProvider.getText("informationLabel"));
        getView().getChooseLineLabel().setText(i18nProvider.getText("chooseLineLabel"));
        getView().getChooseStopLabel().setText(i18nProvider.getText("chooseStopLabel"));
        getView().getChangeDirectionButton().setText(i18nProvider.getText("changeDirectionButton"));
        getView().getRefreshWeatherButton().setText(i18nProvider.getText("refreshWeatherButton"));
        getView().getWeekButton().setText(i18nProvider.getText("week"));
        getView().getWeekendButton().setText(i18nProvider.getText("weekend"));
        setLabelWeather();
    }

    private void setLabelWeather() {
        log.info("Set label with weather");

        I18nProvider i18nProvider = I18nProvider.getInstance();
        String weatherTitle;
        if(ifInternetConnectionIsAvailable()){
            weatherTitle = weatherForecast.getMain().getTempInCelsius() + " °C, " + I18nProvider.getInstance().getText("cityName");
        }else{
            weatherTitle = i18nProvider.getText("notInternetConnection");
        }
        getView().getWeatherLabel().setText(weatherTitle);
    }

    private Boolean ifInternetConnectionIsAvailable(){
        log.info("Checking internet connection...");

        Boolean response;
        try {
            URL url = new URL("http://www.google.com");
            URLConnection conn = url.openConnection();
            conn.connect();
            response = true;
        } catch (IOException e) {
            response = false;
            log.info("Cannot connect to the internet!");
        }

        return response;
    }

    private void updateWeather(String cityName) throws IOException, UpdateWeatherException {
        log.info("Updating weather...");
        OpenWeatherMapClient client = new OpenWeatherMapClient();

        try {
            weatherForecast = client.getWeatherForecast(cityName);
            getView().getWeatherImage().setImage(new Image(weatherForecast.getURLIcon()));
        } catch (NotInternetConnectionException e) {
            log.info("NotInternetConnectionException!!!");
            log.info(e.getMessage());
            throw new UpdateWeatherException();
        } finally {
            setLabelWeather();
        }
    }

    private void setActionOnClickSearchByLineButton() {
        getView().getSearchByLineButton().setOnAction(e -> {
            log.info("Click line button!");
            clearCenterPane();
            getView().generateElementButtonList(DatabaseManager.getInstance().getAllObjectList(LineData.class));
            setFirstStepActionOnClickLineButton();
        });
    }

    private void setActionOnClickSearchByStopButton() {
        getView().getSearchByStopButton().setOnAction(e -> {
            log.info("Click stop button!");
            clearCenterPane();
            getView().generateElementTableList(DatabaseManager.getInstance().getAllObjectList(Stop.class));
            setFirstStepActionOnDoubleClickOnElementStopListView();
        });
    }

    private void setActionOnClickShowCityMapButton() {
        getView().getShowCityMapButton().setOnAction(e -> {
            log.info("Click show map button!");
            GoogleMapClient googleMapClient = new GoogleMapClient();
            try {
                getView().getGoogleMapWebView().getEngine().load(googleMapClient.getURLMap().toString());
                clearCenterPane();
                getView().getCenterPane().getChildren().add(getView().getGoogleMapWebView());
            } catch (NotFoundHTMLException e1) {
                log.info(e1.getMessage());
            }
        });
    }

    private void setActionOnClickChangeDirectionButton(){
        getView().getChangeDirectionButton().setOnAction(e -> {
            log.info("Click change direction button!");
            changeDirection();
            try {
                generateTimetable();
            } catch (NotTimetableForDirectionStopException e1) {
                log.info("You cant change direction because: stop = stopDirectrion");
            }
        });
    }

    private void setActionOnClickLanguageMenuItem() {
        HashMap<String, MenuItem> hashMap = getView().getMenuItemsMap().get(Language.class.getSimpleName());
        for (Map.Entry<String, MenuItem> entry : hashMap.entrySet()) {
            entry.getValue().setOnAction(e -> {
                log.info("generate language..." + " " + entry.getKey());
                I18nProvider.getInstance().changeLanguage(Language.valueOf(entry.getKey().toUpperCase()).asLocale());
                setTexts();
            });
        }
    }

    private void setActionOnClickAppStyleMenuItem() {
        HashMap<String, MenuItem> hashMap = getView().getMenuItemsMap().get(Style.class.getSimpleName());
        for (Map.Entry<String, MenuItem> entry : hashMap.entrySet()) {
            log.info("generate style... " + entry.getKey());
            entry.getValue().setOnAction(e -> AppStyle.getInstance().setStyle(getView(), Style.valueOf(entry.getKey().toUpperCase())));
        }
    }

    private void setActionOnClickRefreshWeatherButton(){
        getView().getRefreshWeatherButton().setOnAction(e -> {
            log.info("click refresh weather button!");
            try {
                updateWeather(ConfigManager.getInstance().getProperty("city"));
                getView().getTextArea().setText(new DateTime().toString() + " " + I18nProvider.getInstance().getText("alertWeather"));
            } catch (UpdateWeatherException e1) {
                log.info(e1.getMessage());
            } catch (IOException e2) {
                log.info(e2.getMessage());
            }
        });
    }

    private void setFirstStepActionOnDoubleClickOnElementStopListView(){
        getView().getStopsListView().setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                log.info("double click on stop!");
                clearCenterPane();
                selectedStopDataId = getIdOfSelectedItemStopsListView();
                getView().generateElementButtonList(DatabaseManager.getInstance().getLinesOfStop(selectedStopDataId));
                setSecondStepActionOnClickLineButton();
            }
        });
    }

    private Long getIdOfSelectedItemStopsListView() {
        return getView().getElementsListHashMap().get(getView().getStopsListView().getSelectionModel().getSelectedItem()).getId();
    }

    private void setFirstStepActionOnClickLineButton(){
        for(LineButton lineButton : getView().getLineButtonsList()){
            lineButton.setOnAction(e -> {
                log.info("click on line button! (first step)");
                clearCenterPane();
                selectedLineDataId = lineButton.getElement().getId();
                getView().generateElementTableList(DatabaseManager.getInstance().getStopsDataOfLine(selectedLineDataId));
                setSecondStepActionOnDoubleClickOnElementStopListView();
            });
        }
    }

    private void setSecondStepActionOnDoubleClickOnElementStopListView(){
        getView().getStopsListView().setOnMouseClicked(e -> {
            if(e.getClickCount() == 2){
                log.info("double click on stop (first step)!");
                clearCenterPane();
                selectedStopDataId = getIdOfSelectedItemStopsListView();
                log.info("generating timetables...");
                //generuj widok z rozkladem jazdy
                checkAndGenerateTimetable();
            }
        });
    }

    private void setSecondStepActionOnClickLineButton(){
        for(LineButton lineButton : getView().getLineButtonsList()){
            lineButton.setOnAction(e -> {
                log.info("click on line button (second step)");
                clearCenterPane();
                selectedLineDataId = lineButton.getElement().getId();
                log.info("generating timetables...");
                selectedStopDataId = DatabaseManager.getInstance().getStopDataIdOfLine(selectedStopDataId, selectedLineDataId);
                //generuj widok z rozkladem jazdy
                checkAndGenerateTimetable();
            });
        }
    }

    private void setOnActionOnClickWeekButton(){
        getView().getWeekButton().setOnAction(e -> {
            selectedDay = "week";
            checkAndGenerateTimetable();
        });
    }

    private void setOnActionOnClickWeekendButton(){
        getView().getWeekendButton().setOnAction(e -> {
            selectedDay = "weekend";
            checkAndGenerateTimetable();
        });
    }

    private void checkAndGenerateTimetable() {
        try {
            generateTimetable();
        } catch (NotTimetableForDirectionStopException e1) {
            log.info("cannot change direction stop = directionStop");
            try {
                generateTimetable();
            } catch (NotTimetableForDirectionStopException e2) {
                log.info("ERROR!!! Unespected error!");
            }
        }
    }

    private void clearCenterPane(){
        if(getView().getCenterPane().getChildren().size() != 0){
            log.info("Clear node from CenterPane!");
            getView().getCenterPane().getChildren().remove(0, getView().getCenterPane().getChildren().size());
        }
    }

    private void generateTimetable() throws NotTimetableForDirectionStopException {
        StopData[] stopDataTab = DatabaseManager.getInstance().getDirectionStops(selectedLineDataId);

        if(selectedStopDataId.equals(stopDataTab[changerDirection].getId())){
            changeDirection();
            throw new NotTimetableForDirectionStopException();
        }

        List<TimetableData> timetableDataList = DatabaseManager.getInstance().getTimetables(selectedStopDataId, stopDataTab[changerDirection].getStopDataId(), selectedLineDataId, selectedDay);

        ObservableList<TimetableElement> data = FXCollections.observableArrayList(TimetableHelper.getTimetableElementList(timetableDataList));
        getView().getTimetableTableView().setItems(data);

        clearCenterPane();

        setInformationLabelText();
        VBox vBox = new VBox();

        vBox.getChildren().addAll(getView().getInformationLabel(),
                getView().getHboxButtons(),
                getView().getTimetableTableView()
        );

        getView().getCenterPane().getChildren().addAll(vBox);

        log.info("Finish generate timetables!");
    }

    private void setInformationLabelText(){
        getView().getInformationLabel()
                .setText(I18nProvider.getInstance().getText("informationLabel")
                        .replace("<stopName>", DatabaseManager.getInstance().getStopData(selectedStopDataId).getStop().getStopName())
                        .replace("<lineNumber>", DatabaseManager.getInstance().getLineData(selectedLineDataId).getNumberLine())
                        .replace("<directionStopName>", DatabaseManager.getInstance().getDirectionStops(selectedLineDataId)[changerDirection].getStop().getStopName())
                        .replace("<day>", I18nProvider.getInstance().getText(selectedDay)));
    }

    private void setGoogleMapWebViewProperty(){
        getView().getGoogleMapWebView().getEngine().getLoadWorker().stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED)
            {
               log.info("Loading website is finished!");
            }
        });
    }

    private void changeDirection(){
        log.info("change direction!");
        if(changerDirection == 1){
            changerDirection = 0;
        }else{
            changerDirection = 1;
        }
    }

}
