package FileType;
import org.w3c.dom.Node;

import javax.swing.text.Document;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;


public class XMLFile {
    public ArrayList<String> readFile(String filename) {
        ArrayList<String> arrayList = new ArrayList<>();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XMLEventReader xmlEventReader = null;
        try {
            xmlEventReader = xmlInputFactory.createXMLEventReader(inputStream);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = null;
            try {
                xmlEvent = xmlEventReader.nextEvent();
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }
            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();
                if (startElement.getName().getLocalPart().equals("Expression")) {
                    try {
                        xmlEvent = xmlEventReader.nextEvent();
                    } catch (XMLStreamException e) {
                        throw new RuntimeException(e);
                    }
                    arrayList.add(xmlEvent.asCharacters().toString());
                }
            }
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource((Node) doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
