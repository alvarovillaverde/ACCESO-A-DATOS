package repaso.empleado;

public class Empleado {
    private String nombre;
    private String dni;

    // Constructor sin parámetros
    public Empleado() {
        this.nombre = "";
        this.dni = "";
    }

    // Constructor con parámetros
    public Empleado(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}