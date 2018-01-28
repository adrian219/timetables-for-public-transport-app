package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config;

import org.joda.time.DateTime;
import org.xml.sax.SAXException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.*;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.exception.NotFoundXMLException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.helper.XMLHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatabaseManager {
    private static DatabaseManager databaseManager;
    private EntityManagerFactory factory;

    private DatabaseManager() {
        factory = Persistence.createEntityManagerFactory(ConfigManager.getInstance().getProperty("persistenceUnitName"));
        System.out.println("Create DatabaseManager...");
    }

    public static DatabaseManager getInstance() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public void loadExampleData(){
        Integer MAX_VALUE = 50;
        EntityManager em = factory.createEntityManager();

        Random random = new Random();

        //LINE DATA
        ArrayList<LineData> lineDataArrayList = new ArrayList<>();

        em.getTransaction().begin();
        for(int i = 1; i<=MAX_VALUE; i++){
            LineData lineData = new LineData(Integer.toString(i));
            em.persist(lineData);
            lineDataArrayList.add(lineData);
        }
        em.getTransaction().commit();

        //VEHICLE DATA
        em.getTransaction().begin();
        for(int i = 1; i<=MAX_VALUE; i++){
            em.persist(new VehicleData("WM1236" + i,
                    lineDataArrayList.get(random.nextInt(MAX_VALUE)),
                    em.createQuery("SELECT p FROM Vehicle p WHERE p.vehicleId=" + (random.nextInt(7)+16), Vehicle.class).getSingleResult()));
        }
        em.getTransaction().commit();

        //STOP DATA
        ArrayList<StopData> stopDataArrayList = new ArrayList<>();

        em.getTransaction().begin();
        for(int i = 1; i<=MAX_VALUE-30; i++){
            StopData stopData = new StopData(i%14,
                    50.0+i,
                    20.0+i,
                    em.createQuery("SELECT p FROM Stop p WHERE p.stopId=" + (random.nextInt(15)+1), Stop.class).getSingleResult());
            em.persist(stopData);
            stopDataArrayList.add(stopData);
        }
        em.getTransaction().commit();

        //TIMETABLES DATA
        em.getTransaction().begin();
        for(int i = 1; i<=MAX_VALUE*3; i++){
            int r = random.nextInt(20);
            em.persist(new TimetableData(new Time(new DateTime().getMillis()+i*1000), "all", stopDataArrayList.get(r), lineDataArrayList.get(random.nextInt(50)), stopDataArrayList.get((r*10)%12)));
        }
        em.getTransaction().commit();

        em.close();
    }

    public void loadDictionaries() throws ParserConfigurationException, IOException, SAXException, NotFoundXMLException {
        loadOneDictionary(XMLHelper.getStopsList());
        loadOneDictionary(XMLHelper.getVehiclesList());
    }

    public <E> List<E> getAllObjectList(Class<E> c){
        EntityManager em = factory.createEntityManager();

        List list = em.createQuery("SELECT p FROM " + c.getSimpleName() + " p", c).getResultList();

        em.close();

        return list;
    }

    public List<TimetableData> getTimetables(StopData stopData, LineData lineData){
        EntityManager em = factory.createEntityManager();

        return em.createQuery(  "SELECT p " +
                                        "FROM TimetableData p " +
                                        "WHERE p.lineData = :lineData " +
                                        "AND p.stopData = :stopData"
                                        , TimetableData.class)
                                        .setParameter("lineData", lineData)
                                        .setParameter("stopData", stopData)
                                        .getResultList();
    }

    private <E> void loadOneDictionary(ArrayList<E> arrayList){
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        for (E vehicle : arrayList) {
            em.persist(vehicle);
        }
        em.getTransaction().commit();

        em.close();
    }
}
