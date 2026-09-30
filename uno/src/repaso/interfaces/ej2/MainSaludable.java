package repaso.interfaces.ej2;

public class MainSaludable {
    public static void main(String[] args) {
        Saludable p = new Persona("Carlos");
        Saludable r = new Robot("R2D2");

        p.saludar();
        r.saludar();
    }
}