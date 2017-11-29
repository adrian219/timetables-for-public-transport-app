package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_vehicles_data")
public class VehicleDataDTO {
    @Id
    @Column(name = "vehicle_data_id")
    private Long vehicleDataId;

    @Column(name = "licence_plate")
    private String licencePlate;

    @Column(name = "line_data_id")
    private Long lineDataId;

    @Column(name = "vehicle_id")
    private Long vehicleId;

    public VehicleDataDTO() {

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

    public Long getLineDataId() {
        return lineDataId;
    }

    public void setLineDataId(Long lineDataId) {
        this.lineDataId = lineDataId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}