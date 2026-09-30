package repaso.interfaces.ej2;

public class Robot implements Saludable {
    private String modelo;

    public Robot(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public void saludar() {
        System.out.println("BEEP BOOP. Saludos, humano. Unidad " + modelo + " iniciada.");
    }
}