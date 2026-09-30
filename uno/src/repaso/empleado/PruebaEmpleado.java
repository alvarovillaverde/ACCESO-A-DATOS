package repaso.empleado;

public class PruebaEmpleado {
    public static void main(String[] args) {
        Empleado e1 = new Empleado("Ana", "12345678A");
        Empleado e2 = new Empleado("Carlos", "87654321B");

        // Modificamos solo el nombre del primer empleado
        e1.setNombre("Ana María");

        // Impresión para comprobar que e2 permanece inalterado
        System.out.println("Empleado 1: " + e1.getNombre() + " - DNI: " + e1.getDni());
        System.out.println("Empleado 2: " + e2.getNombre() + " - DNI: " + e2.getDni());
    }
}