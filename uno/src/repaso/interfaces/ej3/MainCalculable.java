package repaso.interfaces.ej3;

public class MainCalculable {
    public static void main(String[] args) {
        Calculable c = new Circulo(5.0);
        Calculable r = new Rectangulo(4.0, 6.0);

        System.out.println("Área del Círculo: " + c.calcularArea());
        System.out.println("Área del Rectángulo: " + r.calcularArea());
    }
}