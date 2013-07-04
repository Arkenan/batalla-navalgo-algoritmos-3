package vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Boton extends JButton {
    int tamX = 218; // ancho de los botones
    int tamY = 54; // altura de los botones

    public Boton(String dirImagen, int posX, int posY) {
        super();
        ImageIcon icono = new ImageIcon(dirImagen);
        this.setIcon(icono);
        this.setBounds(posX, posY, tamX, tamY);
    }
}
