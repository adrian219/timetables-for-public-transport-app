package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dto;

import javax.persistence.*;

@Entity
@Table(name = "t_lines_data")
public class LineData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_data_id")
    private Long lineDataId;

    @Column(name = "number_line")
    private String numberLine;

    public LineData() {

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