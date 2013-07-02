package vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Boton extends JButton {
    int tamX = 250;
    int tamY = 50;

    public Boton(int posX, int posY, String direccionIm, String nombre) {
        super(nombre);
        ImageIcon icono = new ImageIcon(direccionIm);
        this.setIcon(icono);
        this.setBounds(posX, posY, tamX, tamY);
    }
}
