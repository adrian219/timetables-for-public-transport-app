package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.dto;

import javafx.scene.paint.Stop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Iterator;

@Entity
@Table(name = "t_stops")
public class StopDTO {
    @Id
    @Column(name = "stop_id")
    private Long stopId;

    @Column(name = "stop_name")
    private String stopName;

    private ArrayList<TimetableDataDTO> timetableDataDTO;
    private ArrayList<StopDataDTO> stopDataDTO;

    public StopDTO() {

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
            newTimetableDataDTO.setStopDTO(this);
        }
    }

    public void removeTimetableDataDTO(TimetableDataDTO oldTimetableDataDTO) {
        if (oldTimetableDataDTO == null)
            return;
        if (this.timetableDataDTO != null)
            if (this.timetableDataDTO.contains(oldTimetableDataDTO)) {
                this.timetableDataDTO.remove(oldTimetableDataDTO);
                oldTimetableDataDTO.setStopDTO((StopDTO) null);
            }
    }

    public void removeAllTimetableDataDTO() {
        if (timetableDataDTO != null) {
            TimetableDataDTO oldTimetableDataDTO;
            for (Iterator iter = getIteratorTimetableDataDTO(); iter.hasNext(); ) {
                oldTimetableDataDTO = (TimetableDataDTO) iter.next();
                iter.remove();
                oldTimetableDataDTO.setStopDTO((StopDTO) null);
            }
        }
    }

    public ArrayList<StopDataDTO> getStopDataDTO() {
        if (stopDataDTO == null)
            stopDataDTO = new ArrayList<StopDataDTO>();
        return stopDataDTO;
    }

    public Iterator getIteratorStopDataDTO() {
        if (stopDataDTO == null)
            stopDataDTO = new ArrayList<StopDataDTO>();
        return stopDataDTO.iterator();
    }

    public void setStopDataDTO(java.util.Collection<StopDataDTO> newStopDataDTO) {
        removeAllStopDataDTO();
        for (Iterator iter = newStopDataDTO.iterator(); iter.hasNext(); )
            addStopDataDTO((StopDataDTO) iter.next());
    }

    public void addStopDataDTO(StopDataDTO newStopDataDTO) {
        if (newStopDataDTO == null)
            return;
        if (this.stopDataDTO == null)
            this.stopDataDTO = new ArrayList<StopDataDTO>();
        if (!this.stopDataDTO.contains(newStopDataDTO)) {
            this.stopDataDTO.add(newStopDataDTO);
            newStopDataDTO.setStopDTO(this);
        }
    }

    public void removeStopDataDTO(StopDataDTO oldStopDataDTO) {
        if (oldStopDataDTO == null)
            return;
        if (this.stopDataDTO != null)
            if (this.stopDataDTO.contains(oldStopDataDTO)) {
                this.stopDataDTO.remove(oldStopDataDTO);
                oldStopDataDTO.setStopDTO((StopDTO) null);
            }
    }

    public void removeAllStopDataDTO() {
        if (stopDataDTO != null) {
            StopDataDTO oldStopDataDTO;
            for (Iterator iter = getIteratorStopDataDTO(); iter.hasNext(); ) {
                oldStopDataDTO = (StopDataDTO) iter.next();
                iter.remove();
                oldStopDataDTO.setStopDTO((StopDTO) null);
            }
        }
    }

}