package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao;

import javax.persistence.*;

@Entity
public class LineData {
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
}