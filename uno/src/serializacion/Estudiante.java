package serializacion;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Estudiante implements Serializable {
    private String nombre;
    private int edad;
    private double notaMedia;
    private transient String contraseña;
    
    public Estudiante(String nombre, int edad, double notaMedia, String contraseña) {
        this.nombre = nombre;
        this.edad = edad;
        this.notaMedia = notaMedia;
        this.contraseña = contraseña;
    }


    @Override
    public String toString() {
        return "Estudiante [nombre=" + nombre + ", edad=" + edad + ", notaMedia=" + notaMedia + ", contraseña="
                + contraseña + "]";
    }

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        String archivo = "estudiante.ser";
        Estudiante uno = new Estudiante("toni", 41, 8.2, "jobamacho");

        System.out.println("Antes de serializar: " + uno);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(uno);
        } catch (IOException e) {
            // TODO: handle exception
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Estudiante leido = (Estudiante) ois.readObject();
            System.out.println("Despues de serializar: " + leido);
        } catch (ClassNotFoundException | IOException e) {
            // TODO: handle exception
        }
    }
}
