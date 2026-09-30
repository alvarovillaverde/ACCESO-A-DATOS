package repaso.interfaces.ej1;

public class AppPublicaciones {

    public static int cuentaPrestados(Object[] lista) {
        int contador = 0;
        for (Object obj : lista) {
            if (obj instanceof Prestable) {
                if (((Prestable) obj).estaPrestado()) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public static int publicacionesAnterioresA(Publicacion[] lista, int ano) {
        int contador = 0;
        for (Publicacion p : lista) {
            if (p.getAnoPublicacion() < ano) {
                contador++;
            }
        }
        return contador;
    }

    public static void main(String[] args) {
        Publicacion[] publicaciones = new Publicacion[4];
        publicaciones[0] = new Libro("L01", "Don Quijote", 1605);
        publicaciones[1] = new Libro("L02", "Cien Años de Soledad", 1967);
        publicaciones[2] = new Revista("R01", "National Geographic", 1995, 120);
        publicaciones[3] = new Revista("R02", "Muy Interesante", 1985, 45);

        // Prestar uno de los libros
        ((Libro) publicaciones[0]).prestar();

        // Mostrar datos almacenados
        System.out.println("--- Lista de Publicaciones ---");
        for (Publicacion p : publicaciones) {
            System.out.println(p);
        }

        // Mostrar estadísticas
        System.out.println("\nPublicaciones prestadas: " + cuentaPrestados(publicaciones));
        System.out.println("Publicaciones anteriores a 1990: " + publicacionesAnterioresA(publicaciones, 1990));
    }
}