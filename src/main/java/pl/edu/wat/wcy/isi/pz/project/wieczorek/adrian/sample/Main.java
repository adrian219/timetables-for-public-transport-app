package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.sample;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.DatabaseManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.HomeController;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.initializer.TimetablesInitializer;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.provider.I18nProvider;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view.HomeView;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //we can setting in properties
        TimetablesInitializer.init();

        setStageProperty(primaryStage);
        setHomePage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        DatabaseManager.getInstance().closeConnection();
    }

    private void setStageProperty(Stage primaryStage){
        I18nProvider provider = I18nProvider.getInstance();
        primaryStage.setTitle(provider.getText("titleLabel"));
//        primaryStage.initStyle(StageStyle.UNDECORATED);
    }

    private void setHomePage(Stage primaryStage) throws IOException, ClassNotFoundException {
        HomeView homeView = new HomeView();
        HomeController homeController = new HomeController(homeView);

        primaryStage.setScene(homeView.asScene());
    }
}
