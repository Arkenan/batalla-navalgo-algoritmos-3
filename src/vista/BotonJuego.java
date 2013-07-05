package vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BotonJuego extends JButton {
	int tamX = 218;
	int tamY = 54;

	public BotonJuego(int posX, int posY, String direccionIm, String nombre) {
		super(nombre);
		ImageIcon icono = new ImageIcon(direccionIm);
		this.setIcon(icono);
		this.setBounds(posX, posY, tamX, tamY);
	}
}
