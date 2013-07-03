package vista;

import java.awt.Color;

import javax.swing.JLabel;

public class Texto extends JLabel {
	private final int tamX = 200;
	private final int tamY = 30;

	public Texto(String texto, int posX, int posY) {
		super(texto);
		this.setForeground(Color.BLACK);
		this.setBounds(posX, posY, tamX, tamY);
	}
}
