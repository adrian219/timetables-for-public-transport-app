package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adrian Wieczorek on 11/22/2017.
 */
public class HomeModel extends Model {
    public void reaction(){
        for(ActionListener listener : getListeners()){
            listener.actionPerformed(new ActionEvent(new Object(), ActionEvent.ACTION_FIRST, "alert"));
        }

        //listener
    }

    public void downloadLineData(){

    }

    public void downloadStopData(){

    }

    public void downloadCityMap(){

    }
}
