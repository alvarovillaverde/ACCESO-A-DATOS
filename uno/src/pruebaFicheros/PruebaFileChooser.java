package pruebaFicheros;

import java.io.File;

import javax.swing.JFileChooser;

public class PruebaFileChooser {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(parent);
        if (seleccion == JFileChooser.APROVE_OPTION)
            {
            File fichero = fileChooser.getSelectedFile();
            // Aquí debemos abrir y leer el fichero.
            }
    }

}
