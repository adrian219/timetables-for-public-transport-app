package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao;

import javax.persistence.*;

@Entity
public class VehicleData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vehicleDataId;

    private String licencePlate;

    @ManyToOne(fetch = FetchType.LAZY)
    private LineData lineData;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle vehicle;

    public VehicleData() {

    }

    public VehicleData(String licencePlate, LineData lineData, Vehicle vehicle) {
        this.licencePlate = licencePlate;
        this.lineData = lineData;
        this.vehicle = vehicle;
    }

    public Long getVehicleDataId() {
        return vehicleDataId;
    }

    public void setVehicleDataId(Long vehicleDataId) {
        this.vehicleDataId = vehicleDataId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public LineData getLineData() {
        return lineData;
    }

    public void setLineData(LineData lineData) {
        this.lineData = lineData;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}