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

    private VehicleDTO vehicleDTO;
    private LineDataDTO lineDataDTO;

    public VehicleDataDTO() {

    }

    public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }

    public void setVehicleDTO(VehicleDTO newVehicleDTO) {
        if (this.vehicleDTO == null || !this.vehicleDTO.equals(newVehicleDTO)) {
            if (this.vehicleDTO != null) {
                VehicleDTO oldVehicleDTO = this.vehicleDTO;
                this.vehicleDTO = null;
                oldVehicleDTO.removeVehicleDataDTO(this);
            }
            if (newVehicleDTO != null) {
                this.vehicleDTO = newVehicleDTO;
                this.vehicleDTO.addVehicleDataDTO(this);
            }
        }
    }

    public LineDataDTO getLineDataDTO() {
        return lineDataDTO;
    }


    public void setLineDataDTO(LineDataDTO newLineDataDTO) {
        if (this.lineDataDTO == null || !this.lineDataDTO.equals(newLineDataDTO)) {
            if (this.lineDataDTO != null) {
                LineDataDTO oldLineDataDTO = this.lineDataDTO;
                this.lineDataDTO = null;
                oldLineDataDTO.removeVehicleDataDTO(this);
            }
            if (newLineDataDTO != null) {
                this.lineDataDTO = newLineDataDTO;
                this.lineDataDTO.addVehicleDataDTO(this);
            }
        }
    }

}