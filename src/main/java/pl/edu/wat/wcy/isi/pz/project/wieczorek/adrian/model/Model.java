package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.model;

import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Adrian Wieczorek on 12/2/2017.
 */
public abstract class Model {
    private ArrayList<ActionListener> listeners = new ArrayList<>();;

    public ArrayList<ActionListener> getListeners() {
        return listeners;
    }

    public void addListener(ActionListener actionlistener){
        listeners.add(actionlistener);
    }

    public void removeListener(ActionListener actionListener){
        listeners.remove(actionListener);
    }
}
