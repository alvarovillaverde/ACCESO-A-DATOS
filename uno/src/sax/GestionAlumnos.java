package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class GestionAlumnos extends DefaultHandler {

    private StringBuilder buffer = new StringBuilder();

    // Atributos del alumno actual
    private String idActual;
    private String nombre;
    private String apellidos;
    private String ciclo;
    private double nota;

    // Acumuladores y estadísticas
    private int totalAlumnos = 0;
    private double sumaNotas = 0.0;
    private int aprobados = 0;
    private int suspensos = 0;

    private String mejorAlumno = "";
    private double notaMax = -1.0;

    private String peorAlumno = "";
    private double notaMin = 11.0;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("=== INICIO DE LA LECTURA DEL DOCUMENTO ===");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("=== FIN DE LA LECTURA DEL DOCUMENTO ===\n");
        
        System.out.println("--- ESTADÍSTICAS ---");
        System.out.println("Número total de alumnos: " + totalAlumnos);
        if (totalAlumnos > 0) {
            System.out.printf("Nota media de la clase: %.2f\n", (sumaNotas / totalAlumnos));
        }
        System.out.println("Alumno con la nota más alta: " + mejorAlumno + " (" + notaMax + ")");
        System.out.println("Alumno con la nota más baja: " + peorAlumno + " (" + notaMin + ")");
        System.out.println("Aprobados: " + aprobados);
        System.out.println("Suspensos: " + suspensos);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        buffer.setLength(0); // Limpiar buffer para el contenido del elemento
        if (qName.equalsIgnoreCase("alumno")) {
            idActual = attributes.getValue("id");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "nombre":
                nombre = buffer.toString().trim();
                break;
            case "apellidos":
                apellidos = buffer.toString().trim();
                break;
            case "ciclo":
                ciclo = buffer.toString().trim();
                break;
            case "nota":
                nota = Double.parseDouble(buffer.toString().trim());
                break;
            case "alumno":
                // Muestra del alumno en consola
                System.out.printf("[%s] %s, %s (%s) - Nota: %.2f\n", idActual, apellidos, nombre, ciclo, nota);

                // Actualizar acumuladores
                totalAlumnos++;
                sumaNotas += nota;

                if (nota >= 5.0) {
                    aprobados++;
                } else {
                    suspensos++;
                }

                String nombreCompleto = apellidos + ", " + nombre;
                if (nota > notaMax) {
                    notaMax = nota;
                    mejorAlumno = nombreCompleto;
                }
                if (nota < notaMin) {
                    notaMin = nota;
                    peorAlumno = nombreCompleto;
                }
                break;
        }
    }
}