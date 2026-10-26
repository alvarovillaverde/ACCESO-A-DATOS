package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionLibreria extends DefaultHandler {

    private StringBuilder buffer = new StringBuilder();

    // Variables para el libro actual
    private String isbn;
    private String idioma;
    private String titulo;
    private List<String> autores = new ArrayList<>();
    private String editorial;
    private int anio;
    private double precio;
    private String moneda;

    // Acumuladores
    private Map<String, Integer> librosPorIdioma = new HashMap<>();
    private double sumaPreciosEUR = 0.0;
    private int contadorLibrosEUR = 0;

    private String libroMasAutores = "";
    private int maxAutores = -1;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("=== TODOS LOS LIBROS DEL CATÁLOGO ===");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        buffer.setLength(0);

        if (qName.equalsIgnoreCase("libro")) {
            isbn = attributes.getValue("isbn");
            idioma = attributes.getValue("idioma");
            autores.clear(); // Reiniciar lista de autores para el nuevo libro
        } else if (qName.equalsIgnoreCase("precio")) {
            moneda = attributes.getValue("moneda");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "titulo":
                titulo = buffer.toString().trim();
                break;
            case "autor":
                autores.add(buffer.toString().trim());
                break;
            case "editorial":
                editorial = buffer.toString().trim();
                break;
            case "anio":
                anio = Integer.parseInt(buffer.toString().trim());
                break;
            case "precio":
                precio = Double.parseDouble(buffer.toString().trim());
                break;
            case "libro":
                String listaAutores = String.join(", ", autores);

                // 1. Mostrar cada libro
                System.out.printf("%s (%d) - %s - %s - %.2f %s - [%s] - %s\n",
                        titulo, anio, listaAutores, editorial, precio, moneda, isbn, idioma);

                // Contar idiomas
                librosPorIdioma.put(idioma, librosPorIdioma.getOrDefault(idioma, 0) + 1);

                // Filtrar euros
                if ("EUR".equalsIgnoreCase(moneda)) {
                    sumaPreciosEUR += precio;
                    contadorLibrosEUR++;
                }

                // Más autores
                if (autores.size() > maxAutores) {
                    maxAutores = autores.size();
                    libroMasAutores = titulo + " (" + maxAutores + " autores)";
                }
                break;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\n=== CONTEO POR IDIOMA ===");
        librosPorIdioma.forEach((idm, cant) -> System.out.println("Idioma [" + idm + "]: " + cant + " libros"));

        System.out.println("\n=== ESTADÍSTICAS EN EUROS (EUR) ===");
        System.out.printf("Precio total: %.2f EUR\n", sumaPreciosEUR);
        if (contadorLibrosEUR > 0) {
            System.out.printf("Precio medio: %.2f EUR\n", (sumaPreciosEUR / contadorLibrosEUR));
        }

        System.out.println("\n=== LIBRO CON MÁS AUTORES ===");
        System.out.println(libroMasAutores);
    }

    // --- CONTROL DE ERRORES ---

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.err.printf("[WARNING] Línea %d, Columna %d: %s\n", e.getLineNumber(), e.getColumnNumber(), e.getMessage());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.err.printf("[ERROR] Línea %d, Columna %d: %s\n", e.getLineNumber(), e.getColumnNumber(), e.getMessage());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.err.printf("[FATAL ERROR] Línea %d, Columna %d: %s\n", e.getLineNumber(), e.getColumnNumber(), e.getMessage());
        throw e; // Detiene el procesamiento
    }
}