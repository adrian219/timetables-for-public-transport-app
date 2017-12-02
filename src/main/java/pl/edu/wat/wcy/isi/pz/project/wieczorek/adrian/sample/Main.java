package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.sample;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.HomeController;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.model.HomeModel;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view.HomeView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        setStageProperty(primaryStage);
        setHomePage(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setStageProperty(Stage primaryStage){
        primaryStage.setTitle("Hello World");
//        primaryStage.initStyle(StageStyle.UNDECORATED);
    }

    private void setHomePage(Stage primaryStage){
        HomeModel homeModel = new HomeModel();
        HomeController homeController = new HomeController(homeModel);
        HomeView homeView = new HomeView(homeController, homeModel, 200, 200);
        primaryStage.setScene(homeView.asScene());
    }
}
