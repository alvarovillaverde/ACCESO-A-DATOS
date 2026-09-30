package repaso.encapsulacion;

public class PruebaRectangulo {
    public static void main(String[] args) {
        Rectangulo r1 = new Rectangulo();

        // Secuencia de setters requerida
        r1.setAlto(3);
        r1.setAncho(6);
        r1.setAlto(-4); // Inválido: mantiene alto = 3
        r1.setAncho(0);  // Inválido: mantiene ancho = 6

        System.out.println("Alto final: " + r1.getAlto());         // Imprime: 3.0
        System.out.println("Ancho final: " + r1.getAncho());       // Imprime: 6.0
        System.out.println("Área: " + r1.area());                  // Imprime: 18.0
        System.out.println("Perímetro: " + r1.perimetro());        // Imprime: 18.0
    }
}
