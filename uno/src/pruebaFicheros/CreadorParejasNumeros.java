package pruebaFicheros;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CreadorParejasNumeros {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nombreArchivo = "parejas.txt";

        System.out.println("=== CREADOR DE ARCHIVO DE PAREJAS DE NÚMEROS ===");
        System.out.println("Introduce dos números enteros separados por un espacio en cada línea.");
        System.out.println("Escribe 'INTRO' al inicio de una línea para finalizar.\n");

        try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo))) {

            while (true) {
                System.out.print("Introduce pareja: ");
                String linea = sc.nextLine().trim();

                if (linea.equalsIgnoreCase("INTRO") || linea.toUpperCase().startsWith("INTRO")) {
                    System.out.println("\nFinalizando la introducción de datos...");
                    break;
                }

                String[] partes = linea.split("\\s+");

                if (partes.length == 2 && esEntero(partes[0]) && esEntero(partes[1])) {
                    escritor.println(partes[0] + " " + partes[1]);
                    System.out.println("Guardado correctamente.");
                } else {
                    System.out.println("Formato incorrecto. Deben ser dos números enteros separados por un espacio.");
                }
            }

            System.out.println("El archivo '" + nombreArchivo + "' ha sido guardado con éxito.");

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static boolean esEntero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}