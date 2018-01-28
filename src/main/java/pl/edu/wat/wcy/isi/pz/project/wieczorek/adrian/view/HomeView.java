package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.helper.ReflectionHelper;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.provider.I18nProvider;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.style.AppStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adrian Wieczorek on 11/22/2017.
 */
public class HomeView extends View{
    private Scene homeScene;
    private BorderPane mainPane;

    //TOP PANE ELEMENTS
    private VBox topPane;
    private ImageView titleImage;
    private MenuBar topMenuBar;

    //fileMenu and items
    private Menu fileMenu;
    private MenuItem closeItem;

    //aboutMenu and items
    private Menu aboutMenu;
    private MenuItem aboutItem;

    private HashMap<String, HashMap<String, MenuItem>> menuItemsMap;
    private HashMap<String, Menu> menuMap;

    //--END--

    //BOTTOM PANE ELEMENTS
    private VBox bottomPane;
    private Label infoLabel;
    private TextArea textArea;

    //LEFT PANE ELEMENTS
    private VBox leftPane;
    private Button searchByLineButton;
    private Button searchByStopButton;
    private Button showCityMapButton;
    private ImageView weatherImage;
    private Label weatherLabel;

    //CENTER PANE ELEMENTS
    private AnchorPane centerPane;

    private Button button;

    public HomeView() throws IOException, ClassNotFoundException {
        super();
        initializeElements();
    }

    private void initializeElements() throws IOException, ClassNotFoundException {
        mainPane = new BorderPane();
        mainPane.setId("mainPane");

        topPane = new VBox();
        topPane.setId("topPane");

        titleImage = new ImageView(new Image("images/bus.gif", 30, 30, false, false));
        titleImage.setId("titleImage");

        topMenuBar = new MenuBar();
        topMenuBar.setId("topMenuBar");

        fileMenu = new Menu();
        fileMenu.setId("fileMenu");

        aboutMenu = new Menu();
        aboutMenu.setId("aboutMenu");

        closeItem = new MenuItem();
        closeItem.setId("closeItem");

        aboutItem = new MenuItem();
        aboutItem.setId("aboutItem");

        menuMap = new HashMap<>();
        menuItemsMap = new HashMap<>();
        initializeMenus();

        bottomPane = new VBox();
        bottomPane.setId("bottomPane");

        infoLabel = new Label();
        infoLabel.setId("infoLabel");

        textArea = new TextArea();
        textArea.setId("textArea");

        leftPane = new VBox();
        leftPane.setId("leftPane");

        searchByLineButton = new Button();
        searchByLineButton.setId("searchByLineButton");

        searchByStopButton = new Button();
        searchByStopButton.setId("searchByStopButton");

        showCityMapButton = new Button();
        showCityMapButton.setId("showCityMapButton");

        weatherImage = new ImageView();
        weatherImage.setId("weatherImage");

        weatherLabel = new Label();
        weatherLabel.setId("weatherLabel");

        centerPane = new AnchorPane();
        centerPane.setId("centerPane");

        button = new Button();
        button.setId("button");

        homeScene = new Scene(createRootPane());

        homeScene.getStylesheets().add(AppStyle.getInstance().getCurrentPathStyle());
    }

    private void initializeMenus() throws IOException, ClassNotFoundException {
        ArrayList<Class<?>> classesList = ReflectionHelper.getClassesImplementingInterface(ReflectionHelper.DEFAULT_PACKAGE + ".enums.MenuElement");

        Menu tempMenu;
        for(Class c : classesList){
            System.out.println(c.getSimpleName().toLowerCase() + "sMenu");
            tempMenu = initializeMenu(c);
            menuMap.put(c.getSimpleName().toLowerCase() + "sMenu", tempMenu);
            topMenuBar.getMenus().add(tempMenu);
        }
    }

    private Menu initializeMenu(Class enumType){
        Menu tempMenu = new Menu();
        String id = enumType.getSimpleName().toLowerCase() + "sMenu";
        tempMenu.setId("menu");
        tempMenu.setText(I18nProvider.getInstance().getText(id));

        HashMap<String, MenuItem> menuItems = initializeMenuItems(enumType);
        tempMenu.getItems().addAll(menuItems.values());

        menuItemsMap.put(enumType.getSimpleName(), menuItems);

        return tempMenu;
    }

    private <V extends Enum<V>> HashMap<String, MenuItem> initializeMenuItems(Class<V> enumType){
        HashMap<String, MenuItem> menuItemsMap = new HashMap<>();
        MenuItem tempMenuItem;
        String id;
        for(Enum element : enumType.getEnumConstants()){
            tempMenuItem = new MenuItem();
            id = element.toString() + "MenuItem";
            System.out.println("elementName: " + id);
            tempMenuItem.setId("menuItem");
            tempMenuItem.setText(I18nProvider.getInstance().getText(id));
            menuItemsMap.put(element.toString(), tempMenuItem);
        }

        return menuItemsMap;
    }

    private BorderPane createRootPane() {
        mainPane = new BorderPane();

        setMainPane();

        setTopPane();
        setBottomPane();
        setLeftPane();
        setCenterPane();

        setSceneStyle();

        return mainPane;
    }

    private void setCenterPane() {
        centerPane.getChildren().add(button);
    }

    private void setLeftPane() {
        leftPane.getChildren().addAll(searchByLineButton, searchByStopButton, showCityMapButton, weatherImage, weatherLabel);
    }

    private void setBottomPane() {
        bottomPane.getChildren().add(textArea);
        bottomPane.getChildren().add(infoLabel);
    }

    private void setTopPane() {
        //topPane.getChildren().add(titleImage);

        fileMenu.getItems().addAll(closeItem);
        aboutMenu.getItems().addAll(aboutItem);

        topMenuBar.getMenus().addAll(fileMenu, aboutMenu);

        topPane.getChildren().add(topMenuBar);
    }

    private void setMainPane() {
        mainPane.setTop(topPane);
        mainPane.setBottom(bottomPane);
        mainPane.setLeft(leftPane);
        mainPane.setCenter(centerPane);
    }

    private void setSceneStyle() {
        leftPane.setPadding(new Insets(10, 10, 10, 10));
        leftPane.setSpacing(10);
        leftPane.alignmentProperty().setValue(Pos.TOP_CENTER);

        searchByLineButton.setMinWidth(50);
        searchByStopButton.setMinWidth(50);
        showCityMapButton.setMinWidth(50);

        //centerPane.alignmentProperty().setValue(Pos.CENTER);

        textArea.setWrapText(true);
        textArea.setMaxHeight(60);
        textArea.setDisable(true);
        textArea.end();
    }

    @Override
    public Scene asScene() {
        return homeScene;
    }

    public BorderPane getMainPane() {
        return mainPane;
    }

    public VBox getTopPane() {
        return topPane;
    }

    public ImageView getTitleImage() {
        return titleImage;
    }

    public MenuBar getTopMenuBar() {
        return topMenuBar;
    }

    public Menu getFileMenu() {
        return fileMenu;
    }

    public Menu getAboutMenu() {
        return aboutMenu;
    }

    public MenuItem getCloseItem() {
        return closeItem;
    }

    public MenuItem getAboutItem() {
        return aboutItem;
    }

    public VBox getBottomPane() {
        return bottomPane;
    }

    public Label getInfoLabel() {
        return infoLabel;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public VBox getLeftPane() {
        return leftPane;
    }

    public Button getSearchByLineButton() {
        return searchByLineButton;
    }

    public Button getSearchByStopButton() {
        return searchByStopButton;
    }

    public Button getShowCityMapButton() {
        return showCityMapButton;
    }

    public AnchorPane getCenterPane() {
        return centerPane;
    }

    public Button getButton() {
        return button;
    }

    public Scene getHomeScene() {
        return homeScene;
    }

    public HashMap<String, HashMap<String, MenuItem>> getMenuItemsMap() {
        return menuItemsMap;
    }

    public HashMap<String, Menu> getMenuMap() {
        return menuMap;
    }

    public ImageView getWeatherImage() {
        return weatherImage;
    }

    public Label getWeatherLabel() {
        return weatherLabel;
    }
}
