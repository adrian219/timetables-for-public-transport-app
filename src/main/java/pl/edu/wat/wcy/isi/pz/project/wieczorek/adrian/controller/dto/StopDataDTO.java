package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Iterator;

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

    private StopDTO stopDTO;

    private ArrayList<TimetableDataDTO> timetableDataDTO;

    public StopDataDTO() {

    }

    public ArrayList<TimetableDataDTO> getTimetableDataDTO() {
        if (timetableDataDTO == null)
            timetableDataDTO = new ArrayList<>();
        return timetableDataDTO;
    }

    public Iterator<TimetableDataDTO> getIteratorTimetableDataDTO() {
        if (timetableDataDTO == null)
            timetableDataDTO = new ArrayList<>();
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
            this.timetableDataDTO = new ArrayList<>();
        if (!this.timetableDataDTO.contains(newTimetableDataDTO)) {
            this.timetableDataDTO.add(newTimetableDataDTO);
            newTimetableDataDTO.setStopDataDTO(this);
        }
    }

    public void removeTimetableDataDTO(TimetableDataDTO oldTimetableDataDTO) {
        if (oldTimetableDataDTO == null)
            return;
        if (this.timetableDataDTO != null)
            if (this.timetableDataDTO.contains(oldTimetableDataDTO)) {
                this.timetableDataDTO.remove(oldTimetableDataDTO);
                oldTimetableDataDTO.setStopDataDTO((StopDataDTO) null);
            }
    }

    public void removeAllTimetableDataDTO() {
        if (timetableDataDTO != null) {
            TimetableDataDTO oldTimetableDataDTO;
            for (Iterator iter = getIteratorTimetableDataDTO(); iter.hasNext(); ) {
                oldTimetableDataDTO = (TimetableDataDTO) iter.next();
                iter.remove();
                oldTimetableDataDTO.setStopDataDTO((StopDataDTO) null);
            }
        }
    }

    public StopDTO getStopDTO() {
        return stopDTO;
    }

    public void setStopDTO(StopDTO newStopDTO) {
        if (this.stopDTO == null || !this.stopDTO.equals(newStopDTO)) {
            if (this.stopDTO != null) {
                StopDTO oldStopDTO = this.stopDTO;
                this.stopDTO = null;
                oldStopDTO.removeStopDataDTO(this);
            }
            if (newStopDTO != null) {
                this.stopDTO = newStopDTO;
                this.stopDTO.addStopDataDTO(this);
            }
        }
    }

}