package ru.job4j.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class ConvertXSQT {
    private static final Logger LOG = LoggerFactory.getLogger(ConvertXSQT.class);

    public void convert(File source, File dest, File scheme) {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(scheme);
        try {
            Transformer transformer = factory.newTransformer(xslt);
            Source text = new StreamSource(source);
            transformer.transform(text, new StreamResult(dest));
        } catch (TransformerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
