package ru.job4j.sqlite;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class Magnit {


    public static void main(String[] args) {
        var magnit = new Magnit();
        magnit.init(23, "magnit_file_one.xml", "magnit_file_two.xml", "magnit_change.xsl");
    }

    public void init(int size, String fileOneName, String fileTwoName, String schemeFileName) {
        var fileOne = new File(fileOneName);
        var fileTwo = new File(fileTwoName);
        var schemeFile = new File(schemeFileName);
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
        var handler = new Handler();
        try {
            var saxParserFactory = SAXParserFactory.newInstance();
            var saxParser = saxParserFactory.newSAXParser();
            var xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return handler.getSum();
    }

    private static class Handler extends DefaultHandler {

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
