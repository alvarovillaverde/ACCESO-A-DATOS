package repaso.interfaces.ej1;

public abstract class Publicacion {
    private String codigo;
    private String titulo;
    private int anoPublicacion;

    public Publicacion(String codigo, String titulo, int anoPublicacion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anoPublicacion = anoPublicacion;
    }

    public int getAnoPublicacion() {
        return anoPublicacion;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + ", Título: " + titulo + ", Año: " + anoPublicacion;
    }
}