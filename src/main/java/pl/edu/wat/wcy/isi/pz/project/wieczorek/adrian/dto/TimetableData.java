package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dto;

import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "t_timetables_data")
public class TimetableData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public TimetableData() {

    }

    public Long getTimetableDataId() {
        return timetableDataId;
    }

    public void setTimetableDataId(Long timetableDataId) {
        this.timetableDataId = timetableDataId;
    }

    public DateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(DateTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public Long getStopDataId() {
        return stopDataId;
    }

    public void setStopDataId(Long stopDataId) {
        this.stopDataId = stopDataId;
    }

    public Long getLineDataId() {
        return lineDataId;
    }

    public void setLineDataId(Long lineDataId) {
        this.lineDataId = lineDataId;
    }

    public Long getStopDirectionId() {
        return stopDirectionId;
    }

    public void setStopDirectionId(Long stopDirectionId) {
        this.stopDirectionId = stopDirectionId;
    }
}