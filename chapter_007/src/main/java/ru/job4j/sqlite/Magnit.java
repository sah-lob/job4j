package ru.job4j.sqlite;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Magnit {


    public static void main(String[] args) {
        var magnit = new Magnit();
        magnit.init(23, "file_one.xml", "file_two.xml", "change.xsl");
    }

    public void init(int size, String fileOnePath, String fileTwoPath, String schemeFilePath) {
        var fileOne = new File("chapter_007/src/main/resources/" + fileOnePath);
        var fileTwo = new File("chapter_007/src/main/resources/" + fileTwoPath);
        var schemeFile = new File("chapter_007/src/main/resources/" + schemeFilePath);
        var config = new Config();
        config.init();
        var storeSQL = new StoreSQL(config);
        storeSQL.init();
        storeSQL.generate(size);
        var entries = storeSQL.load();
        var storeXML = new StoreXML(fileOne);
        storeXML.save(entries);
        var convertXSQT = new ConvertXSQT();
        convertXSQT.convert(fileOne, fileTwo, schemeFile);
        var sum = sum(fileTwo);
        System.out.println("Сумма всех элементов: " + sum);
        System.out.println("Программа успешно выполнена. ");
    }


    public int sum(File file) {
        var myHandler = new MyHandler();
        try {
            var saxParserFactory = SAXParserFactory.newInstance();
            var saxParser = saxParserFactory.newSAXParser();
            var xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(myHandler);
            xmlReader.parse(file.getPath());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myHandler.getSum();
    }

    private static class MyHandler extends DefaultHandler {

        private int sum = 0;
        private static final String ENTRY_TAG = "entry";
        private static final String ENTRY_HREF_ATTRIBUTE = "href";

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals(ENTRY_TAG)) {
               sum += Integer.parseInt(attributes.getValue(ENTRY_HREF_ATTRIBUTE));
            }
        }

        public int getSum() {
            return sum;
        }
    }
}
