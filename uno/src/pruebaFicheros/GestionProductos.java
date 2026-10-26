package pruebaFicheros;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class GestionProductos {

    private static final int TAMANO_REGISTRO = 40;
    private static final int LONGITUD_NOMBRE = 12;

    public static void main(String[] args) {
        String[] nombres = {"Manzana", "Pera", "Plátano", "Naranja", "Melocotón"};
        int[] existencias = {100, 150, 200, 80, 50};
        double[] precios = {1.50, 1.80, 2.10, 1.20, 2.50};

        File archivo = new File("productos.dat");
        
        if (archivo.exists()) {
            archivo.delete();
        }

        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw");
             Scanner scanner = new Scanner(System.in)) {

            for (int i = 0; i < nombres.length; i++) {
                raf.writeInt(i + 1);
                StringBuffer sb = new StringBuffer(nombres[i]);
                sb.setLength(LONGITUD_NOMBRE);
                raf.writeChars(sb.toString());
                raf.writeInt(existencias[i]);
                raf.writeDouble(precios[i]);
            }

            System.out.println("--- LISTADO DE PRODUCTOS ---");
            mostrarTodos(raf);

            System.out.print("\nIntroduce el ID del producto a consultar (1-5): ");
            int idConsulta = scanner.nextInt();
            if (esIdValido(idConsulta, nombres.length)) {
                long pos = (idConsulta - 1) * TAMANO_REGISTRO;
                raf.seek(pos);
                System.out.println("Producto encontrado:");
                mostrarProductoActual(raf);
            } else {
                System.out.println("ID no válido.");
            }

            System.out.print("\nIntroduce el ID del producto para actualizar existencias: ");
            int idEx = scanner.nextInt();
            if (esIdValido(idEx, nombres.length)) {
                System.out.print("Introduce la nueva cantidad de existencias: ");
                int nuevasExistencias = scanner.nextInt();
                if (nuevasExistencias >= 0) {
                    long pos = (idEx - 1) * TAMANO_REGISTRO + 4 + 24;
                    raf.seek(pos);
                    raf.writeInt(nuevasExistencias);
                    System.out.println("Existencias actualizadas.");
                } else {
                    System.out.println("Las existencias no pueden ser negativas.");
                }
            } else {
                System.out.println("ID no válido.");
            }

            System.out.print("\nIntroduce el ID del producto para actualizar precio: ");
            int idPr = scanner.nextInt();
            if (esIdValido(idPr, nombres.length)) {
                System.out.print("Introduce el nuevo precio: ");
                double nuevoPrecio = scanner.nextDouble();
                if (nuevoPrecio >= 0) {
                    long pos = (idPr - 1) * TAMANO_REGISTRO + 4 + 24 + 4;
                    raf.seek(pos);
                    raf.writeDouble(nuevoPrecio);
                    System.out.println("Precio actualizado.");
                } else {
                    System.out.println("El precio no puede ser negativo.");
                }
            } else {
                System.out.println("ID no válido.");
            }

            System.out.println("\n--- LISTADO DE PRODUCTOS TRAS LAS MODIFICACIONES ---");
            mostrarTodos(raf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean esIdValido(int id, int totalRegistros) {
        return id >= 1 && id <= totalRegistros;
    }

    private static void mostrarTodos(RandomAccessFile raf) throws IOException {
        raf.seek(0);
        long numRegistros = raf.length() / TAMANO_REGISTRO;
        for (int i = 0; i < numRegistros; i++) {
            mostrarProductoActual(raf);
        }
    }

    private static void mostrarProductoActual(RandomAccessFile raf) throws IOException {
        int id = raf.readInt();
        char[] nombreArr = new char[LONGITUD_NOMBRE];
        for (int j = 0; j < LONGITUD_NOMBRE; j++) {
            nombreArr[j] = raf.readChar();
        }
        String nombre = new String(nombreArr).trim();
        int existencias = raf.readInt();
        double precio = raf.readDouble();

        System.out.println("ID: " + id + " | Nombre: " + nombre + " | Existencias: " + existencias + " | Precio: " + precio + "€");
    }
}