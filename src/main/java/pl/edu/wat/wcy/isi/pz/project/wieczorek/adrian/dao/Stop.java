package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao;

import javax.persistence.*;

@Entity
@Table(name = "t_stops")
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stop_id")
    private Long stopId;

    @Column(name = "stop_name")
    private String stopName;

    public Stop() {

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