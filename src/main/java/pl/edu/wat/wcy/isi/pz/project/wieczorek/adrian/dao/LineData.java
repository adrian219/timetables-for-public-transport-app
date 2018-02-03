package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao;

import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.util.ShowElement;

import javax.persistence.*;

@Entity
public class LineData extends ShowElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lineDataId;

    private String numberLine;

    public LineData() {

    }

    public LineData(String numberLine) {
        this.numberLine = numberLine;
    }

    public Long getLineDataId() {
        return lineDataId;
    }

    public void setLineDataId(Long lineDataId) {
        this.lineDataId = lineDataId;
    }

    public String getNumberLine() {
        return numberLine;
    }

    public void setNumberLine(String numberLine) {
        this.numberLine = numberLine;
    }

    @Override
    public String getText(){
        return numberLine;
    }

    @Override
    public Long getId() {
        return lineDataId;
    }


}