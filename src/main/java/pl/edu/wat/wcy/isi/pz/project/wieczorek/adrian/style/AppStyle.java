package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.style;

import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.enums.Style;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view.View;

public class AppStyle {
    private static final String FOLDER_PATH = "styles/";
    private final Style defaultStyle = Style.YELLOW;
    private static AppStyle appStyle;
    private Style currentStyle;

    private AppStyle(){
        currentStyle = defaultStyle;
    }

    public static AppStyle getInstance(){
        if(appStyle == null){
            appStyle = new AppStyle();
        }

        return appStyle;
    }

    public String getCurrentPathStyle(){
        return FOLDER_PATH + currentStyle.getFileName();
    }

    public void setStyle(View view, Style style){
        removeCurrentStyle(view);
        view.asScene().getStylesheets().add(getPathStyle(style));
    }

    private void removeCurrentStyle(View view){
        view.asScene().getStylesheets().clear();
    }

    private String getPathStyle(Style style){
        return FOLDER_PATH + style.getFileName();
    }
}
