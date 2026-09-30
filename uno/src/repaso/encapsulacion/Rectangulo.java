package repaso.encapsulacion;

public class Rectangulo {
    private double alto = 1;
    private double ancho = 1;

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        if (alto > 0) {
            this.alto = alto;
        }
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        if (ancho > 0) {
            this.ancho = ancho;
        }
    }

    public double area() {
        return alto * ancho;
    }

    public double perimetro() {
        return 2 * (alto + ancho);
    }
}