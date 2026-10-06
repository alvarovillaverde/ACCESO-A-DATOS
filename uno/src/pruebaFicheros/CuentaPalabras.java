package pruebaFicheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CuentaPalabras {
    public static void main(String[] args) {
        File archivo = new File("./uno/src/pruebaFicheros/fermin.txt");
        int contador = 0;
        String linea = "";

        try {
            Scanner lectura=new Scanner(archivo);
            while (lectura.hasNextLine()){
            linea = lectura.nextLine();
            StringTokenizer tk = new StringTokenizer(linea);
            contador += tk.countTokens();
            System.out.println(linea);
        }
            lectura.close();
        }
            catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("El archivo tiene: " + contador);
    }
    
}
