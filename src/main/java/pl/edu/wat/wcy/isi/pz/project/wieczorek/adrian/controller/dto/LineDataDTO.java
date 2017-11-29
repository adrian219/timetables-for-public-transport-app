package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_lines_data")
public class LineDataDTO {
    @Id
    @Column(name = "line_data_id")
    private Long lineDataId;

    @Column(name = "number_line")
    private String numberLine;

    public LineDataDTO() {

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