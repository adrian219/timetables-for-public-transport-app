package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view.util;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class TimetableElement {
    private SimpleStringProperty hour;
    private SimpleStringProperty minutes;

    public TimetableElement(Integer hour, ArrayList<Integer> minutesList) {
        this.hour = new SimpleStringProperty(hour > 9 ? hour.toString() : "0" + hour.toString());
        StringBuilder builder = new StringBuilder();
        for(Integer i : minutesList){
            builder
                    .append("  ")
                    .append(i>9 ? i : "0" + i.toString());
        }

        minutes = new SimpleStringProperty(builder.toString());
    }

    public String getHour() {
        return hour.get();
    }

    public SimpleStringProperty hourProperty() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour.set(hour);
    }

    public String getMinutes() {
        return minutes.get();
    }

    public SimpleStringProperty minutesProperty() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes.set(minutes);
    }
}
