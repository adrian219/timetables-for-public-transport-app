package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.helper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.ConfigManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.Stop;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.Vehicle;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.exception.NotFoundXMLException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.provider.I18nProvider;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class XMLHelper {
    public static ArrayList<Stop> getStopsList() throws ParserConfigurationException, NotFoundXMLException, SAXException, IOException {
        Document doc = parseXML(ConfigManager.getInstance().getProperty("stopsFileName"));
        ArrayList<Stop> stopsList = new ArrayList<>();

        NodeList nodeList = doc.getElementsByTagName("stop");

        Stop tempStop;
        for(int i = 0; i<nodeList.getLength(); i++){
            Element element = (Element)nodeList.item(i);

            tempStop = new Stop();
            tempStop.setStopName(element.getElementsByTagName("name").item(0).getTextContent());
            stopsList.add(tempStop);
        }

        return stopsList;
    }

    public static ArrayList<Vehicle> getVehiclesList() throws NotFoundXMLException, IOException, SAXException, ParserConfigurationException {
        Document doc = parseXML(ConfigManager.getInstance().getProperty("vehiclesFileName"));
        ArrayList<Vehicle> vehiclesList = new ArrayList<>();

        NodeList nodeList = doc.getElementsByTagName("vehicle");

        Vehicle tempVehicle;
        for(int i = 0; i<nodeList.getLength(); i++){
            Element element = (Element)nodeList.item(i);

            tempVehicle = new Vehicle();
            tempVehicle.setVehicleType(element.getElementsByTagName("type").item(0).getTextContent());
            tempVehicle.setForDisabledPerson(Boolean.parseBoolean(element.getElementsByTagName("forDisabled").item(0).getTextContent()));
            vehiclesList.add(tempVehicle);
        }

        return vehiclesList;
    }

    private static Document parseXML(String xml) throws ParserConfigurationException, IOException, SAXException, NotFoundXMLException {
        File fileXML = getXMLFile(xml);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fileXML);

        return doc;
    }

    private static File getXMLFile(String xml) throws NotFoundXMLException {
        URL url = (new XMLHelper()).getClass().getClassLoader().getResource("dictionaries/" + xml);

        if(url == null){
            throw new NotFoundXMLException();
        }

        return new File(url.getFile().replace("%20", " "));
    }

    public static void main(String[] args){
//        try {
//            ArrayList<Stop> list = getStopsList(parseXML("stops.xml"));
//
//            for(Stop stop : list){
//                System.out.println(stop.toString());
//            }
//
//            ArrayList<Vehicle> list2 = getVehiclesList(parseXML("vehicles.xml"));
//
//            for(Vehicle vehicle : list2){
//                System.out.println(vehicle.toString());
//            }
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (NotFoundXMLException e) {
//            e.printStackTrace();
//        }
    }
}
