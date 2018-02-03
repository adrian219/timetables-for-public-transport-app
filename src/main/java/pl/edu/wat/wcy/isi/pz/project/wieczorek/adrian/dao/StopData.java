package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao;

import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.util.ShowElement;

import javax.persistence.*;

@Entity
public class StopData extends ShowElement{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stopDataId;

    @ManyToOne(fetch = FetchType.EAGER)
    private LineData lineData;

    @ManyToOne(fetch = FetchType.EAGER)
    private Stop stop;

    private int seqStopNumber;

    public StopData() {
    }

    public StopData(int seqStopNumber, LineData lineData, Stop stop) {
        this.seqStopNumber = seqStopNumber;
        this.lineData = lineData;
        this.stop = stop;
    }

    public Long getStopDataId() {
        return stopDataId;
    }

    public void setStopDataId(Long stopDataId) {
        this.stopDataId = stopDataId;
    }

    public int getSeqStopNumber() {
        return seqStopNumber;
    }

    public void setSeqStopNumber(int stopNumber) {
        this.seqStopNumber = stopNumber;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public LineData getLineData() {
        return lineData;
    }

    public void setLineData(LineData lineData) {
        this.lineData = lineData;
    }

    @Override
    public String getText() {
        return "(" + seqStopNumber + ")" + " " + stop.getStopName(); //i moze jakis numer?
    }

    @Override
    public Long getId() {
        return stopDataId;
    }
}