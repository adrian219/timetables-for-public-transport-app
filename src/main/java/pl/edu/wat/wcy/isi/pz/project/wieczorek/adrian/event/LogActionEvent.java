package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.event;

import javafx.event.ActionEvent;

/**
 * Created by Adrian Wieczorek on 12/5/2017.
 */
public class LogActionEvent extends ActionEvent {
    String info;

    public LogActionEvent(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void sendLogToServer(){
        //send information log to server (using class Logger)
    }
}
