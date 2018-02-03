package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao;

import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.util.ShowElement;

import javax.persistence.*;

@Entity
public class Stop extends ShowElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stopId;

    private String stopName;

    private Double latitude;

    private Double longitude;

    public Stop() {
    }

    public Stop(String stopName, Double latitude, Double longitude) {
        this.stopName = stopName;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String getText(){
        return stopName;
    }

    @Override
    public Long getId() {
        return stopId;
    }


}