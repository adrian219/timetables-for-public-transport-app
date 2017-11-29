package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
@Table(name = "t_vehicles")
public class VehicleDTO {
    @Id
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "for_disabled_person")
    private Boolean forDisabledPerson;

    public ArrayList<VehicleDataDTO> vehicleDataDTO;

    public VehicleDTO() {

    }

    public ArrayList<VehicleDataDTO> getVehicleDataDTO() {
        if (vehicleDataDTO == null)
            vehicleDataDTO = new ArrayList<VehicleDataDTO>();
        return vehicleDataDTO;
    }

    public Iterator<VehicleDataDTO> getIteratorVehicleDataDTO() {
        if (vehicleDataDTO == null)
            vehicleDataDTO = new ArrayList<VehicleDataDTO>();
        return vehicleDataDTO.iterator();
    }

    public void setVehicleDataDTO(ArrayList<VehicleDataDTO> newVehicleDataDTO) {
        removeAllVehicleDataDTO();
        for (Iterator iter = newVehicleDataDTO.iterator(); iter.hasNext(); )
            addVehicleDataDTO((VehicleDataDTO) iter.next());
    }

    public void addVehicleDataDTO(VehicleDataDTO newVehicleDataDTO) {
        if (newVehicleDataDTO == null)
            return;
        if (this.vehicleDataDTO == null)
            this.vehicleDataDTO = new ArrayList<VehicleDataDTO>();
        if (!this.vehicleDataDTO.contains(newVehicleDataDTO)) {
            this.vehicleDataDTO.add(newVehicleDataDTO);
            newVehicleDataDTO.setVehicleDTO(this);
        }
    }

    public void removeVehicleDataDTO(VehicleDataDTO oldVehicleDataDTO) {
        if (oldVehicleDataDTO == null)
            return;
        if (this.vehicleDataDTO != null)
            if (this.vehicleDataDTO.contains(oldVehicleDataDTO)) {
                this.vehicleDataDTO.remove(oldVehicleDataDTO);
                oldVehicleDataDTO.setVehicleDTO((VehicleDTO) null);
            }
    }

    public void removeAllVehicleDataDTO() {
        if (vehicleDataDTO != null) {
            VehicleDataDTO oldVehicleDataDTO;
            for (Iterator iter = getIteratorVehicleDataDTO(); iter.hasNext(); ) {
                oldVehicleDataDTO = (VehicleDataDTO) iter.next();
                iter.remove();
                oldVehicleDataDTO.setVehicleDTO((VehicleDTO) null);
            }
        }
    }

}