package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.helper;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.TimetableData;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.view.util.TimetableElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimetableHelper {
    private static Logger log = LoggerFactory.getLogger(TimetableHelper.class);

    private static HashMap<Integer, ArrayList<Integer>> getMinuteDeparturesOfHour(List<TimetableData> timetableDataList){
        log.info("Get minute departures of hour");
        HashMap<Integer, ArrayList<Integer>> resultHashMap = new HashMap<>();

        for(int i = 0; i<24; i++){
            resultHashMap.put(i, new ArrayList<>());
        }

        DateTime tempDateTime;
        for(TimetableData data : timetableDataList){
            tempDateTime = new DateTime(data.getDepartureTime().getTime());
            resultHashMap.get(tempDateTime.getHourOfDay()).add(tempDateTime.getMinuteOfHour());
        }

        return resultHashMap;
    }

    public static ArrayList<TimetableElement> getTimetableElementList(List<TimetableData> timetableDataList){
        log.info("Get timetables element list");

        HashMap <Integer, ArrayList<Integer>> hashMap = getMinuteDeparturesOfHour(timetableDataList);

        ArrayList<TimetableElement> resultList = new ArrayList<>();

        for(Map.Entry<Integer, ArrayList<Integer>> entry : hashMap.entrySet()){
            resultList.add(new TimetableElement(entry.getKey(), entry.getValue()));
        }

        return resultList;
    }
}
