package repaso.app;

import repaso.modelo.Modelo;


public class PruebaModelo {

    public static void main(String[] args) {
        
    String texto = "Repasando POO";

    Modelo cualquiera = new Modelo(texto);
    
    System.out.println(cualquiera.getTexto());
    }
}


