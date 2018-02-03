package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.component.LineButton;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.util.ShowElement;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.helper.ReflectionHelper;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.provider.I18nProvider;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.style.AppStyle;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view.util.TimetableElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adrian Wieczorek on 11/22/2017.
 */
public class HomeView extends View{
    private Scene homeScene;
    private BorderPane mainPane;

    //TOP PANE ELEMENTS
    private VBox topPane;
    private MenuBar topMenuBar;

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
    private Button refreshWeatherButton;

    //CENTER PANE ELEMENTS
    private VBox centerPane;
    private HBox secondStepCenterPane;
    private List<LineButton> lineButtonsList;
    private ListView<String> stopsListView;
    private Button backButton;
    private HBox hboxButtons;
    private Button changeDirectionButton;
    private Button weekButton;
    private Button weekendButton;
    private Label informationLabel;
    private Label chooseLineLabel;
    private Label chooseStopLabel;

    private TableView timetableTableView;
    private TableColumn hourColumn;
    private TableColumn minuteColumn;

    private WebView googleMapWebView;

    HashMap<String, ShowElement> elementsListHashMap = new HashMap<>();
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

        topMenuBar = new MenuBar();
        topMenuBar.setId("topMenuBar");

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

        backButton = new Button();
        backButton.setId("backButton");

        weatherImage = new ImageView();
        weatherImage.setId("weatherImage");

        weatherLabel = new Label();
        weatherLabel.setId("weatherLabel");

        refreshWeatherButton = new Button();
        refreshWeatherButton.setId("searchByLineButton");

        centerPane = new VBox();
        centerPane.setId("centerPane");

        secondStepCenterPane = new HBox();
        secondStepCenterPane.setId("centerPane");

        lineButtonsList = new ArrayList<>();

        stopsListView = new ListView<>();
        stopsListView.setId("stopsListView");

        timetableTableView = new TableView<>();
        timetableTableView.setId("timetableTableView");

        hourColumn = new TableColumn();
        hourColumn.setId("hourColumn");
        minuteColumn = new TableColumn();
        minuteColumn.setId("minuteColumn");

        informationLabel = new Label();
        informationLabel.setId("informationLabel");

        chooseLineLabel = new Label();
        chooseLineLabel.setId("chooseLineLabel");

        chooseStopLabel = new Label();
        chooseStopLabel.setId("chooseStopLabel");

        changeDirectionButton = new Button();
        changeDirectionButton.setId("searchByLineButton");

        weekButton = new Button();
        weekButton.setId("searchByLineButton");

        weekendButton = new Button();
        weekendButton.setId("searchByLineButton");

        hboxButtons = new HBox();
        hboxButtons.setId("hboxButtons");

        googleMapWebView = new WebView();
        googleMapWebView.setId("googleMapWebView");

        button = new Button();
        button.setId("button");

        homeScene = new Scene(createRootPane());

        homeScene.getStylesheets().add(AppStyle.getInstance().getCurrentPathStyle());

        setTimetableTableViewProperty();
        setInformationLabelProperty();
        setGoogleMapWebViewProperty();
        setHBoxButtons();
    }

    private void initializeMenus() throws IOException, ClassNotFoundException {
        ArrayList<Class<?>> classesList = ReflectionHelper.getClassesImplementingInterface(ReflectionHelper.DEFAULT_PACKAGE + ".enums.MenuElement");

        Menu tempMenu;
        for(Class c : classesList){
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
            tempMenuItem.setId("menuItem");
            tempMenuItem.setText(I18nProvider.getInstance().getText(id));
            menuItemsMap.put(element.toString(), tempMenuItem);
        }

        return menuItemsMap;
    }

    public <E extends ShowElement> void generateElementButtonList(List<E> elementList){
        lineButtonsList.clear();

        LineButton tempButton;
        for(E e : elementList){
            tempButton = new LineButton<>(e);
            lineButtonsList.add(tempButton);
            tempButton.setId("searchByLineButton");
        }
        centerPane.getChildren().add(chooseLineLabel);
        centerPane.getChildren().addAll(lineButtonsList);
    }

    public <E extends ShowElement> void generateElementTableList(List<E> elementList){
        stopsListView.getItems().clear();
        elementsListHashMap = new HashMap<>();
        ObservableList list = FXCollections.observableArrayList();
        for(E e : elementList){
            elementsListHashMap.put(e.getText(), e);
            list.add(e.getText());
        }

        SortedList<String> sortedList = new SortedList(list);
        stopsListView.getItems().addAll(sortedList.sorted());
        centerPane.getChildren().add(chooseStopLabel);
        centerPane.getChildren().add(stopsListView);
        setListViewProperty(stopsListView);
    }

    private void setListViewProperty(ListView listView) {
        listView.prefHeightProperty().bind(centerPane.heightProperty());
        listView.prefWidthProperty().bind(centerPane.widthProperty());
    }

    private void setWeatherLabelProperty(){
        //weatherLabel.prefWidthProperty().bind(searchByLineButton.widthProperty());
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

    }

    private void setLeftPane() {
        leftPane.getChildren().addAll(searchByLineButton,
                searchByStopButton,
                showCityMapButton,
                weatherImage,
                weatherLabel,
                refreshWeatherButton
        );
        setWeatherLabelProperty();
    }

    private void setBottomPane() {
        bottomPane.getChildren().add(textArea);
        bottomPane.getChildren().add(infoLabel);
    }

    private void setTopPane() {
        //topPane.getChildren().add(titleImage);

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

        centerPane.setSpacing(5);
        centerPane.setPadding(new Insets(10, 10, 10, 10));

        textArea.setWrapText(true);
        textArea.setMaxHeight(60);
        textArea.setDisable(true);
        textArea.end();
    }

    private void setTimetableTableViewProperty(){
        hourColumn.setCellValueFactory(
                new PropertyValueFactory<TimetableElement, String>("hour"));

        minuteColumn.setCellValueFactory(
                new PropertyValueFactory<TimetableElement, String>("minutes"));

        timetableTableView.getColumns().addAll(hourColumn, minuteColumn);
        timetableTableView.setEditable(false);
        minuteColumn.setResizable(false);
        hourColumn.setResizable(false);
        minuteColumn.prefWidthProperty().bind(timetableTableView.widthProperty().multiply(0.9));
        hourColumn.prefWidthProperty().bind(timetableTableView.widthProperty().multiply(0.08));
        hourColumn.setMinWidth(25);
        timetableTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void setInformationLabelProperty(){
        informationLabel.setMinHeight(70);
    }

    private void setGoogleMapWebViewProperty() {
        googleMapWebView.prefHeightProperty().bind(centerPane.heightProperty());
        googleMapWebView.prefWidthProperty().bind(centerPane.widthProperty());
        googleMapWebView.getEngine().setJavaScriptEnabled(true);
    }

    private void setHBoxButtons(){
        hboxButtons.getChildren().addAll(changeDirectionButton, weekButton, weekendButton);
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

    public MenuBar getTopMenuBar() {
        return topMenuBar;
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

    public VBox getCenterPane() {
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

    public HBox getSecondStepCenterPane() {
        return secondStepCenterPane;
    }

    public List<LineButton> getLineButtonsList() {
        return lineButtonsList;
    }

    public ListView<String> getStopsListView() {
        return stopsListView;
    }

    public Button getBackButton() {
        return backButton;
    }

    public HashMap<String, ShowElement> getElementsListHashMap() {
        return elementsListHashMap;
    }

    public TableView getTimetableTableView() {
        return timetableTableView;
    }

    public TableColumn getHourColumn() {
        return hourColumn;
    }

    public TableColumn getMinuteColumn() {
        return minuteColumn;
    }

    public Button getChangeDirectionButton() {
        return changeDirectionButton;
    }

    public Button getWeekButton() {
        return weekButton;
    }

    public Button getWeekendButton() {
        return weekendButton;
    }

    public Label getInformationLabel() {
        return informationLabel;
    }

    public Label getChooseLineLabel() {
        return chooseLineLabel;
    }

    public Label getChooseStopLabel() {
        return chooseStopLabel;
    }

    public Button getRefreshWeatherButton() {
        return refreshWeatherButton;
    }

    public WebView getGoogleMapWebView() {
        return googleMapWebView;
    }

    public HBox getHboxButtons() {
        return hboxButtons;
    }
}
