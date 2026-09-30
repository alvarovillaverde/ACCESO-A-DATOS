package repaso.excepciones;

public class Calculadora {
    public static double dividir(double numerador, double denominador) throws ExcepcionIntervalo{
        if (numerador > 100 || denominador < -5) {
            throw new ExcepcionIntervalo();
        }

        return (double) numerador/denominador;
    }
}
