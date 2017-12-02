package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dto;

import javax.persistence.*;

@Entity
@Table(name = "t_vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "for_disabled_person")
    private Boolean forDisabledPerson;

    public Vehicle() {

    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Boolean getForDisabledPerson() {
        return forDisabledPerson;
    }

    public void setForDisabledPerson(Boolean forDisabledPerson) {
        this.forDisabledPerson = forDisabledPerson;
    }
}