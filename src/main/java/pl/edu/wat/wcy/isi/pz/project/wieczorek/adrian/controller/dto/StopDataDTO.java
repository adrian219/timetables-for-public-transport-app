package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_stops_data")
public class StopDataDTO {
    @Id
    @Column(name = "stop_data_id")
    private Long stopDataId;

    @Column(name = "stop_number")
    private short stopNumber;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "stop_id")
    private Long stopId;

    public StopDataDTO() {

    }

    public Long getStopDataId() {
        return stopDataId;
    }

    public void setStopDataId(Long stopDataId) {
        this.stopDataId = stopDataId;
    }

    public short getStopNumber() {
        return stopNumber;
    }

    public void setStopNumber(short stopNumber) {
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

    public Long getStopId() {
        return stopId;
    }

    public void setStopId(Long stopId) {
        this.stopId = stopId;
    }
}