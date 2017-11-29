package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.controller.dto;

import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_timetables_data")
public class TimetableDataDTO {
    @Id
    @Column(name = "timetable_data_id")
    private Long timetableDataId;

    @Column(name = "departure_time")
    private DateTime departureTime;

    @Column(name = "day_of_the_week")
    private String dayOfTheWeek;

    @Column(name = "stop_data_id")
    private Long stopDataId;

    @Column(name = "line_data_id")
    private Long lineDataId;

    @Column(name = "stop_direction_id")
    private Long stopDirectionId;

    private StopDTO stopDTO;
    private LineDataDTO lineDataDTO;
    private StopDataDTO stopDataDTO;

    public TimetableDataDTO() {

    }

    public StopDTO getStopDTO() {
        return stopDTO;
    }

    public void setStopDTO(StopDTO newStopDTO) {
        if (this.stopDTO == null || !this.stopDTO.equals(newStopDTO)) {
            if (this.stopDTO != null) {
                StopDTO oldStopDTO = this.stopDTO;
                this.stopDTO = null;
                oldStopDTO.removeTimetableDataDTO(this);
            }
            if (newStopDTO != null) {
                this.stopDTO = newStopDTO;
                this.stopDTO.addTimetableDataDTO(this);
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
                oldLineDataDTO.removeTimetableDataDTO(this);
            }
            if (newLineDataDTO != null) {
                this.lineDataDTO = newLineDataDTO;
                this.lineDataDTO.addTimetableDataDTO(this);
            }
        }
    }

    public StopDataDTO getStopDataDTO() {
        return stopDataDTO;
    }

    public void setStopDataDTO(StopDataDTO newStopDataDTO) {
        if (this.stopDataDTO == null || !this.stopDataDTO.equals(newStopDataDTO)) {
            if (this.stopDataDTO != null) {
                StopDataDTO oldStopDataDTO = this.stopDataDTO;
                this.stopDataDTO = null;
                oldStopDataDTO.removeTimetableDataDTO(this);
            }
            if (newStopDataDTO != null) {
                this.stopDataDTO = newStopDataDTO;
                this.stopDataDTO.addTimetableDataDTO(this);
            }
        }
    }

}