package repaso.excepciones;

import javax.swing.JOptionPane;

public class AppGrafica {

    public static void main(String[] args) {
        try {
            // 1. Pedir numerador y denominador mediante ventanas de diálogo
            String inputNumerador = JOptionPane.showInputDialog(null, "Introduce el numerador:");
            String inputDenominador = JOptionPane.showInputDialog(null, "Introduce el denominador:");

            // Convertir texto a entero (Si se introducen letras, salta NumberFormatException)
            int numerador = Integer.parseInt(inputNumerador);
            int denominador = Integer.parseInt(inputDenominador);

            // 2. Llamar al método dividir (Si denominador = 0, salta ArithmeticException en enteros)
            double resultado = Calculadora.dividir(numerador, denominador);

            // 3. Mostrar el resultado si todo ha ido bien
            JOptionPane.showMessageDialog(null, "El resultado de la división es: " + resultado);

        } catch (NumberFormatException e) {
            // Entrada no numérica
            JOptionPane.showMessageDialog(null, 
                "Error: Has introducido caracteres no numéricos.", 
                "Error de Entrada", 
                JOptionPane.ERROR_MESSAGE);

        } catch (ExcepcionIntervalo e) {
            // Excepción personalizada
            JOptionPane.showMessageDialog(null, 
                "Error de Intervalo: " + e.getMessage(), 
                "Límite Superado", 
                JOptionPane.WARNING_MESSAGE);

        } catch (ArithmeticException e) {
            // División entre cero
            JOptionPane.showMessageDialog(null, 
                "Error Matemático: No se puede dividir entre cero.", 
                "División por Cero", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}