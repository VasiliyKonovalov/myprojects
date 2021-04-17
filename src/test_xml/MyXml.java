package test_xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class MyXml {
    public static void main(String[] args) throws IOException {
        writeXML();
        readXML();
    }

    public static void writeXML() throws IOException {
        // Объявляем строку, в которой будет xml
        String xmlStringValue = null;
        // Создаем фабрику для создания документа xml
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            // Создаем документ xml через DocumentBuilder
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            // Создаем корневой элемент xml с именем Person
            Element rootElement = doc.createElement("Person");
            // Добавляем элемент Person в наш xml-документ
            doc.appendChild(rootElement);
            // Создаем элемент xml с именем FirstName
            Element firstElement = doc.createElement("FirstName");
            // Добавляем текст Sergey в элемент FirstName
            firstElement.appendChild(doc.createTextNode("Vasiliy"));
            // Создаем элемент xml с именем SecondName
            Element secondElement = doc.createElement("SecondName");
            // Добавляем текст Konovalov в элемент SecondName
            secondElement.appendChild(doc.createTextNode("Konovalov"));
            // Добавляем элемент FirstName внутрь Person
            rootElement.appendChild(firstElement);
            // Добавляем элемент SecondName внутрь Person
            rootElement.appendChild(secondElement);
            // Теперь преобразуем xml-документ в строку
            // Для этого создаем TransformerFactory
            TransformerFactory tf = TransformerFactory.newInstance();
            // Из TransformerFactory получаем Transformer
            Transformer transformer = tf.newTransformer();
            // Преобразуем xml-документ в строку
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            xmlStringValue = writer.getBuffer().toString();
        } catch(ParserConfigurationException | TransformerException e){
            e.printStackTrace();
        }
        // Пишем строку с xml в файл
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("xml/person.xml"))) {
            bufferedWriter.write(xmlStringValue);
            bufferedWriter.close();
        }
    }

    public static void readXML() throws IOException {
        // Открываем файл xml
        File xmlFile = new File("d:/person.xml");
        // Создаем фабрику для создания xml-документа
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            // Парсим документ xml из файла через DocumentBuilder
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlFile);
            // Читаем корневой элемент xml
            Element rootElement = doc.getDocumentElement();
            System.out.println("Корневой элемент: " + rootElement.getNodeName());
            // Ищем элемент FirstName
            NodeList nodeList = rootElement.getElementsByTagName("FirstName");
            if(nodeList.getLength() == 0) {
                System.out.println("Ошибка! Элемент FirstName не найден");
                return;
            }
            Node firstName = nodeList.item(0);
            System.out.println("FirstName: " + firstName.getTextContent());
            // Ищем элемент SecondName
            nodeList = rootElement.getElementsByTagName("SecondName");
            if(nodeList.getLength() == 0) {
                System.out.println("Ошибка! Элемент SecondName не найден");
                return;
            }
            Node secondName = nodeList.item(0);
            System.out.println("SecondName: " + secondName.getTextContent());
        } catch(ParserConfigurationException | SAXException e){
            e.printStackTrace();
        }
    }
}
