package by.issoft.store;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class XmlReader {

    private String ConfigFilePath = "store\\src\\main\\resources\\config.xml";

    public XmlReader() throws ParserConfigurationException, IOException, SAXException {
    }

    public Map<String, String> getPropertiesToSort() throws ParserConfigurationException, IOException, SAXException {

        String sortTag = "sort";

        Map<String, String> propertiesMap = new LinkedHashMap<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(ConfigFilePath);

        Node node = document.getElementsByTagName(sortTag).item(0);
        NodeList sortProperties = node.getChildNodes();

        Element elements;
        for (int i = 0; i < sortProperties.getLength(); i++) {
            if (sortProperties.item(i).getNodeType() == Node.ELEMENT_NODE) {
                elements = (Element) sortProperties.item(i);

                propertiesMap.put(elements.getTagName().toLowerCase(Locale.ROOT), elements.getNodeValue().toLowerCase(Locale.ROOT));
            }

        }
        return propertiesMap;
    }

}
