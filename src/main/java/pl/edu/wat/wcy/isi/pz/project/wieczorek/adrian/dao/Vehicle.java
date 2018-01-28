package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao;

import javax.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vehicleId;

    private String vehicleType;

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