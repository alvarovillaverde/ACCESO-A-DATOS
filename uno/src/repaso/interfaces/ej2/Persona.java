package repaso.interfaces.ej2;

public class Persona implements Saludable {
    private String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void saludar() {
        System.out.println("¡Hola! Mucho gusto, soy la persona " + nombre + ".");
    }
}