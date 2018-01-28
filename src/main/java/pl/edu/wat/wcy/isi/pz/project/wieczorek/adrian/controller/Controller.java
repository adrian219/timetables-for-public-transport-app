package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller;

/**
 * Created by Adrian Wieczorek on 12/2/2017.
 */
public abstract class Controller<V> {
    private V view;

    public Controller(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }

    public void setView(V view){
        this.view = view;
    }
}
