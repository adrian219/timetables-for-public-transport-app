package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.*;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.exception.NotFoundXMLException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.helper.XMLHelper;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DatabaseManager {
    private static DatabaseManager databaseManager;
    private EntityManagerFactory factory;

    private Logger log = LoggerFactory.getLogger(DatabaseManager.class);

    private DatabaseManager() {
        factory = Persistence.createEntityManagerFactory(ConfigManager.getInstance().getProperty("persistenceUnitName"));
        log.info("Create DatabaseManager...");
    }

    public static DatabaseManager getInstance() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public void loadExampleData2() {
        EntityManager em = factory.createEntityManager();

        List<Stop> stopsLine1 = em.createQuery("SELECT p FROM Stop p " +
                "WHERE p.stopName = 'Dworzec gdanski' " +
                "OR p.stopName = 'Ratusz Arsenal' " +
                "OR p.stopName = 'Swietokrzyska' " +
                "OR p.stopName = 'Centrum' " +
                "OR p.stopName = 'Politechnika' " +
                "OR p.stopName = 'Pole mokotowskie' " +
                "OR p.stopName = 'Raclawicka'", Stop.class).getResultList();

        log.info("size list: " + stopsLine1.size());

        em.getTransaction().begin();
        LineData line1 = new LineData("1");
        em.persist(line1);
        em.getTransaction().commit();

        List<StopData> stopDataList = new LinkedList<>();
        StopData tempStopData;
        Integer i = 1;
        em.getTransaction().begin();
        for(Stop stop : stopsLine1){
            tempStopData = new StopData(i, line1, stop);
            stopDataList.add(tempStopData);
            em.persist(tempStopData);
            i++;
        }
        em.getTransaction().commit();

        List<Stop> stopsLine2 = em.createQuery("SELECT p FROM Stop p " +
                "WHERE p.stopName = 'Dworzec wilenski' " +
                "OR p.stopName = 'Stadion narodowy' " +
                "OR p.stopName = 'Centrum Nauki Kopernik' " +
                "OR p.stopName = 'Uniwersytet' " +
                "OR p.stopName = 'Swietokrzyska' " +
                "OR p.stopName = 'Rondo ONZ' " +
                "OR p.stopName = 'Rondo Daszynskiego'", Stop.class).getResultList();

        em.getTransaction().begin();
        LineData line2 = new LineData("2");
        em.persist(line2);
        em.getTransaction().commit();

        List<StopData> stopDataList2 = new LinkedList<>();
        i = 1;
        em.getTransaction().begin();
        for(Stop stop : stopsLine2){
            tempStopData = new StopData(i, line2, stop);
            stopDataList2.add(tempStopData);
            i++;
            em.persist(tempStopData);
        }
        em.getTransaction().commit();

        Random random = new Random();

        em.getTransaction().begin();
        Integer[] tab = {0, stopDataList.size()-1};
        int r;
        for (i = 1; i <= 200; i++) {
            r = random.nextInt(stopDataList.size());
            DateTime dateTime = new DateTime();
            dateTime = new DateTime(dateTime.getMillis()-dateTime.getMillisOfDay()).plusMinutes(i);
            em.persist(new TimetableData(new Time(dateTime.getMillis()), "week", stopDataList.get(1), line1, stopDataList.get(tab[r%2])));
        }

        Integer[] tab2 = {0, stopDataList2.size()-1};
        for (i = 1; i <= 206; i++) {
            r = random.nextInt(stopDataList2.size());
            DateTime dateTime = new DateTime();
            dateTime = new DateTime(dateTime.getMillis()-dateTime.getMillisOfDay()).plusMinutes(i);
            em.persist(new TimetableData(new Time(dateTime.getMillis()), "weekend", stopDataList2.get(r), line2, stopDataList2.get(tab2[r%2])));
        }

        em.getTransaction().commit();

        em.close();
    }

    public void loadDictionaries() throws ParserConfigurationException, IOException, SAXException, NotFoundXMLException {
        loadOneDictionary(XMLHelper.getStopsList());
    }

    public <E> List<E> getAllObjectList(Class<E> c) {
        EntityManager em = factory.createEntityManager();

        List list = em.createQuery("SELECT p FROM " + c.getSimpleName() + " p", c).getResultList();

        em.close();

        return list;
    }

    public List getStopsDataOfLine(Long lineId){
        EntityManager em = factory.createEntityManager();

        return em.createQuery("SELECT p " +
                                "FROM StopData p " +
                                "WHERE p.lineData.lineDataId = :lineData")
                .setParameter("lineData", lineId)
                .getResultList();
    }

    //metoda wyciagajaca liste linii dla okreslonego Stop (czyli szukam w StopData takich, ktorzy sa podpieci do linii)
    public List getLinesOfStop(Long stopId){
        EntityManager em = factory.createEntityManager();

        return em.createQuery("SELECT p " +
                                "FROM Stop s " +
                                "JOIN StopData sd ON s.stopId = sd.stop.stopId " +
                                "JOIN LineData p ON p.lineDataId = sd.lineData.lineDataId " +
                                "WHERE s.stopId = :stopId")
                .setParameter("stopId", stopId)
                .getResultList();
    }

    public List<TimetableData> getTimetables(Long stopId, Long stopDirectionId, Long lineDataId, String day) {
        EntityManager em = factory.createEntityManager();

        List<TimetableData> returnList = em.createQuery("SELECT p " +
                        "FROM TimetableData p " +
                        "WHERE p.lineData.lineDataId = :lineData " +
                        "AND p.stopData.stopDataId = :stop " +
                        "AND p.stopDirection.stopDataId = :stopDirection " +
                        "AND p.dayOfTheWeek = :day"
                , TimetableData.class)
                .setParameter("lineData", lineDataId)
                .setParameter("stop", stopId)
                .setParameter("stopDirection", stopDirectionId)
                .setParameter("day", day)
                .getResultList();

        return returnList;
    }

    public LineData getLineData(Long id){
        EntityManager em = factory.createEntityManager();

        LineData lineData = em.createQuery("SELECT p FROM LineData p WHERE p.lineDataId = :id", LineData.class).setParameter("id", id).getSingleResult();

        em.close();

        return lineData;
    }

    public StopData getStopData(Long id){
        EntityManager em = factory.createEntityManager();

        StopData stopData = em.createQuery("SELECT p FROM StopData p WHERE p.stopDataId = :id", StopData.class).setParameter("id", id).getSingleResult();

        em.close();

        return stopData;
    }

    public StopData[] getDirectionStops(Long lineDataId){
        EntityManager em = factory.createEntityManager();

        StopData stopData1 = em.createQuery("SELECT p " +
                    "FROM StopData p " +
                    "WHERE p.lineData.lineDataId = :lineDataId " +
                    "ORDER BY p.seqStopNumber ASC", StopData.class)
                .setParameter("lineDataId", lineDataId)
                .setMaxResults(1)
                .getSingleResult();

        StopData stopData2 = em.createQuery("SELECT p " +
                "FROM StopData p " +
                "WHERE p.lineData.lineDataId = :lineDataId " +
                "ORDER BY p.seqStopNumber DESC", StopData.class)
                .setParameter("lineDataId", lineDataId)
                .setMaxResults(1)
                .getSingleResult();

        StopData[] stopDataTab = new StopData[2];
        stopDataTab[0] = stopData1;
        stopDataTab[1] = stopData2;

        return stopDataTab;
    }

    public Long getStopDataIdOfLine(Long stopId, Long lineDataId){
        EntityManager em = factory.createEntityManager();

        StopData stopData = em.createQuery("SELECT p " +
                        "FROM StopData p " +
                        "WHERE p.stop.stopId = :stopId " +
                        "AND p.lineData.lineDataId = :lineDataId"
                , StopData.class)
                .setParameter("stopId", stopId)
                .setParameter("lineDataId", lineDataId)
                .getSingleResult();

        return stopData.getId();
    }

    private <E> void loadOneDictionary(ArrayList<E> arrayList) {
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        for (E vehicle : arrayList) {
            em.persist(vehicle);
        }
        em.getTransaction().commit();

        em.close();
    }

    public void closeConnection() {
        log.info("close connection");
        factory.close();
    }
}
