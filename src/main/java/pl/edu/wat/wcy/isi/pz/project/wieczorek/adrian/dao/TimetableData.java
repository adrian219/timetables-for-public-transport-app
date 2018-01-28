package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao;

import org.hibernate.annotations.Fetch;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Time;

@Entity
public class TimetableData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long timetableDataId;

    private Time departureTime;

    private String dayOfTheWeek;

    @ManyToOne(fetch = FetchType.LAZY)
    private StopData stopData;

    @ManyToOne(fetch = FetchType.LAZY)
    private LineData lineData;

    @ManyToOne(fetch = FetchType.LAZY)
    private StopData stopDirection;

    public TimetableData() {

    }

    public TimetableData(Time departureTime, String dayOfTheWeek, StopData stopData, LineData lineData, StopData stopDirection) {
        this.departureTime = departureTime;
        this.dayOfTheWeek = dayOfTheWeek;
        this.stopData = stopData;
        this.lineData = lineData;
        this.stopDirection = stopDirection;
    }

    public Long getTimetableDataId() {
        return timetableDataId;
    }

    public void setTimetableDataId(Long timetableDataId) {
        this.timetableDataId = timetableDataId;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public StopData getStopDataId() {
        return stopData;
    }

    public void setStopData(Long stopDataId) {
        this.stopData = stopData;
    }

    public LineData getLineData() {
        return lineData;
    }

    public void setLineData(LineData lineDataId) {
        this.lineData = lineData;
    }

    public StopData getStopDirection() {
        return stopDirection;
    }

    public void setStopDirection(StopData stopDirection) {
        this.stopDirection = stopDirection;
    }
}