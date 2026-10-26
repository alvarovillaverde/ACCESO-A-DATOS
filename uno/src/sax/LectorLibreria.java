package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class LectorLibreria {
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        System.out.println("==============================================");
        System.out.println("1. PROCESANDO 'libreria.xml'");
        System.out.println("==============================================");
        try {
            SAXParser saxParser = factory.newSAXParser();
            GestionLibreria handler = new GestionLibreria();
            saxParser.parse(new File("libreria.xml"), handler);
        } catch (Exception e) {
            System.err.println("Error procesando libreria.xml: " + e.getMessage());
        }

        System.out.println("\n==============================================");
        System.out.println("2. PROCESANDO 'libreria_erroneo.xml'");
        System.out.println("==============================================");
        try {
            SAXParser saxParser = factory.newSAXParser();
            GestionLibreria handlerErroneo = new GestionLibreria();
            saxParser.parse(new File("libreria_erroneo.xml"), handlerErroneo);
        } catch (Exception e) {
            System.err.println("Procesamiento detenido por error sintáctico.");
        }
    }
}