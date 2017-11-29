package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
@Table(name = "t_lines_data")
public class LineDataDTO {
    @Id
    @Column(name = "line_data_id")
    private Long lineDataId;

    @Column(name = "number_line")
    private String numberLine;

    private ArrayList<VehicleDataDTO> vehicleDataDTO;
    private ArrayList<TimetableDataDTO> timetableDataDTO;

    public LineDataDTO() {

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
            newVehicleDataDTO.setLineDataDTO(this);
        }
    }

    public void removeVehicleDataDTO(VehicleDataDTO oldVehicleDataDTO) {
        if (oldVehicleDataDTO == null)
            return;
        if (this.vehicleDataDTO != null)
            if (this.vehicleDataDTO.contains(oldVehicleDataDTO)) {
                this.vehicleDataDTO.remove(oldVehicleDataDTO);
                oldVehicleDataDTO.setLineDataDTO((LineDataDTO) null);
            }
    }

    public void removeAllVehicleDataDTO() {
        if (vehicleDataDTO != null) {
            VehicleDataDTO oldVehicleDataDTO;
            for (java.util.Iterator iter = getIteratorVehicleDataDTO(); iter.hasNext(); ) {
                oldVehicleDataDTO = (VehicleDataDTO) iter.next();
                iter.remove();
                oldVehicleDataDTO.setLineDataDTO((LineDataDTO) null);
            }
        }
    }

    public ArrayList<TimetableDataDTO> getTimetableDataDTO() {
        if (timetableDataDTO == null)
            timetableDataDTO = new ArrayList<TimetableDataDTO>();
        return timetableDataDTO;
    }

    public Iterator getIteratorTimetableDataDTO() {
        if (timetableDataDTO == null)
            timetableDataDTO = new ArrayList<TimetableDataDTO>();
        return timetableDataDTO.iterator();
    }

    public void setTimetableDataDTO(ArrayList<TimetableDataDTO> newTimetableDataDTO) {
        removeAllTimetableDataDTO();
        for (Iterator iter = newTimetableDataDTO.iterator(); iter.hasNext(); )
            addTimetableDataDTO((TimetableDataDTO) iter.next());
    }

    public void addTimetableDataDTO(TimetableDataDTO newTimetableDataDTO) {
        if (newTimetableDataDTO == null)
            return;
        if (this.timetableDataDTO == null)
            this.timetableDataDTO = new ArrayList<TimetableDataDTO>();
        if (!this.timetableDataDTO.contains(newTimetableDataDTO)) {
            this.timetableDataDTO.add(newTimetableDataDTO);
            newTimetableDataDTO.setLineDataDTO(this);
        }
    }

    public void removeTimetableDataDTO(TimetableDataDTO oldTimetableDataDTO) {
        if (oldTimetableDataDTO == null)
            return;
        if (this.timetableDataDTO != null)
            if (this.timetableDataDTO.contains(oldTimetableDataDTO)) {
                this.timetableDataDTO.remove(oldTimetableDataDTO);
                oldTimetableDataDTO.setLineDataDTO((LineDataDTO) null);
            }
    }

    public void removeAllTimetableDataDTO() {
        if (timetableDataDTO != null) {
            TimetableDataDTO oldTimetableDataDTO;
            for (java.util.Iterator iter = getIteratorTimetableDataDTO(); iter.hasNext(); ) {
                oldTimetableDataDTO = (TimetableDataDTO) iter.next();
                iter.remove();
                oldTimetableDataDTO.setLineDataDTO((LineDataDTO) null);
            }
        }
    }

}