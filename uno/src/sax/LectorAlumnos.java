package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class LectorAlumnos {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            GestionAlumnos handler = new GestionAlumnos();
            saxParser.parse(new File("alumnos.xml"), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}