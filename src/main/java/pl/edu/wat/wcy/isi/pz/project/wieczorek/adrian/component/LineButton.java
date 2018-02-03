package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.component;

import javafx.scene.control.Button;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.util.ShowElement;

public class LineButton<E extends ShowElement> extends Button{
    private E element;

    public LineButton(E element) {
        super();
        this.element = element;
        setId("timetableButton");
        setText(element.getText());
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }
}
