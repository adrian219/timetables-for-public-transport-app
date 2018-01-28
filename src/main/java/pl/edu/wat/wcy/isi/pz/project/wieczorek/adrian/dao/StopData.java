package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao;

import javax.persistence.*;

@Entity
public class StopData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stopDataId;

    private int stopNumber;

    private Double latitude;

    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    private Stop stop;

    public StopData() {

    }

    public StopData(int stopNumber, Double latitude, Double longitude, Stop stop) {
        this.stopNumber = stopNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stop = stop;
    }

    public Long getStopDataId() {
        return stopDataId;
    }

    public void setStopDataId(Long stopDataId) {
        this.stopDataId = stopDataId;
    }

    public int getStopNumber() {
        return stopNumber;
    }

    public void setStopNumber(int stopNumber) {
        this.stopNumber = stopNumber;
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

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }
}