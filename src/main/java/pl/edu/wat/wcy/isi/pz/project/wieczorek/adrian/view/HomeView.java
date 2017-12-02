package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.HomeController;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.HomeController;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.model.HomeModel;

/**
 * Created by Adrian Wieczorek on 11/22/2017.
 */
public class HomeView {
    private HomeController controller;
    private HomeModel model;

    private Scene homeScene;
    private Button button = new Button();
    private Label label = new Label();

    public HomeView(HomeController controller, HomeModel model, double width, double height){
        this.controller = controller;
        this.model = model;

        configureController();

        StackPane root = new StackPane();
        button.setText("Go!");
        root.getChildren().add(button);
        root.getChildren().add(label);
        homeScene = new Scene(root, width, height);
    }

    public Scene getHomeScene(){
        return homeScene;
    }

    private void showAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("content");
        alert.show();
    }

    public Scene asScene() {
        return homeScene;
    }

    private void configureController(){
        label.textProperty().addListener((obs, oldV, newV) -> showAlert());

        button.setOnAction(value -> controller.onClickButton());
    }

    private void configureModel(){
//        label.textProperty().addListener();
    }
}
