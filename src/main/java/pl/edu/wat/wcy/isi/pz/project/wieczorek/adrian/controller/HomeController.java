package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import org.joda.time.DateTime;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.enums.Language;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.enums.Style;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.provider.I18nProvider;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.style.AppStyle;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view.HomeView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adrian Wieczorek on 12/2/2017.
 */
public class HomeController extends Controller<HomeView> {

    public HomeController(HomeView view) {
        super(view);
        configureController();
    }

    private void showAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("content");
        alert.show();
    }

    private void configureController(){
        setActionOnClickTestButton();
        setActionOnClickSearchByStopButton();
        setActionOnClickSearchByLineButton();
        setActionOnClickShowCityMapButton();
        setActionOnClickLanguageMenuItem();
        setActionOnClickAppStyleMenuItem();
    }

    private void setActionOnClickTestButton(){
        getView().getButton().setOnAction(e ->
            getView().getTextArea().setText(getView().getTextArea().getText() + DateTime.now().toString() + ": Awaria komputera" + "\n")
        );
    }

    private void setActionOnClickSearchByLineButton(){
        getView().getSearchByLineButton().setOnAction(e -> {
            //getModel().downloadLineData();
        });
    }

    private void setActionOnClickSearchByStopButton(){
        getView().getSearchByStopButton().setOnAction(e -> {
            //getModel().downloadStopData();
        });
    }

    private void setActionOnClickShowCityMapButton(){
        getView().getShowCityMapButton().setOnAction(e -> {
            //getModel().downloadCityMap();
        });
    }

    private void setActionOnClickLanguageMenuItem(){
        HashMap<String, MenuItem> hashMap = getView().getMenuItemsMap().get(Language.class.getSimpleName());
        for(Map.Entry<String, MenuItem> entry : hashMap.entrySet()){
            System.out.println("entry: " + entry.getKey());
            entry.getValue().setOnAction(e -> {
                I18nProvider.getInstance().changeLanguage(Language.valueOf(entry.getKey().toUpperCase()).asLocale());
                getView().setTexts();
            });
        }
    }

    private void setActionOnClickAppStyleMenuItem(){
        HashMap<String, MenuItem> hashMap = getView().getMenuItemsMap().get(Style.class.getSimpleName());
        for(Map.Entry<String, MenuItem> entry : hashMap.entrySet()){
            System.out.println("entry style: " + entry.getKey());
            entry.getValue().setOnAction(e -> AppStyle.getInstance().setStyle(getView(), Style.valueOf(entry.getKey().toUpperCase())));
        }
    }

}
