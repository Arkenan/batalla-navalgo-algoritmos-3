package vista;


public class VentanaFondo extends VentanaGeneral {

    public VentanaFondo(String titulo, String dirImage) {
        super(titulo);
        this.addImagenDeFondo(dirImage);
    }

    /*
     * private void addImagenDeFondo(String direccion) { ImageIcon iconoNuevaPartida = new
     * ImageIcon(direccion); JLabel icono = new JLabel(iconoNuevaPartida); icono.setBounds(10, 40,
     * 50, 100); this.getContentPane().add(icono); }
     */
}
