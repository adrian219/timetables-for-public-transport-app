package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_stops")
public class StopDTO {
    @Id
    @Column(name = "stop_id")
    private Long stopId;

    @Column(name = "stop_name")
    private String stopName;

    public StopDTO() {

    }

    public Long getStopId() {
        return stopId;
    }

    public void setStopId(Long stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }
}