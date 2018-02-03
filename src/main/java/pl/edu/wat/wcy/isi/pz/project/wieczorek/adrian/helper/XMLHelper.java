package pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.config.ConfigManager;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.dao.Stop;
import pl.edu.wat.wcy.isi.pz.project.wieczorek.adrian.exception.NotFoundXMLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class XMLHelper {
    private static Logger log = LoggerFactory.getLogger(XMLHelper.class);

    public static ArrayList<Stop> getStopsList() throws ParserConfigurationException, NotFoundXMLException, SAXException, IOException {
        log.info("Get all stops list");

        Document doc = parseXML(ConfigManager.getInstance().getProperty("stopsFileName"));
        ArrayList<Stop> stopsList = new ArrayList<>();

        NodeList nodeList = doc.getElementsByTagName("stop");

        Stop tempStop;
        for(int i = 0; i<nodeList.getLength(); i++){
            Element element = (Element)nodeList.item(i);

            try{
                tempStop = new Stop(element.getElementsByTagName("name").item(0).getTextContent(), Double.parseDouble(element.getElementsByTagName("latitude").item(0).getTextContent()), Double.parseDouble(element.getElementsByTagName("longitude").item(0).getTextContent()));
            }catch(NumberFormatException e){
                log.info("cannot parse number");
                log.info(e.getMessage());
                tempStop = new Stop(element.getElementsByTagName("name").item(0).getTextContent(), 0.0, 0.0);
            }
            stopsList.add(tempStop);
        }

        return stopsList;
    }

    private static Document parseXML(String xml) throws ParserConfigurationException, IOException, SAXException, NotFoundXMLException {
        log.info("Start parse XML");

        File fileXML = getXMLFile(xml);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fileXML);

        log.info("Finish parse XML");

        return doc;
    }

    private static File getXMLFile(String xml) throws NotFoundXMLException {
        log.info("Searching xml file...");

        URL url = (new XMLHelper()).getClass().getClassLoader().getResource("dictionaries/" + xml);

        if(url == null){
            throw new NotFoundXMLException();
        }

        return new File(url.getFile().replace("%20", " "));
    }
}
