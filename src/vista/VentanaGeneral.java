package vista;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaGeneral extends JFrame {

    JFrame frame;

    public VentanaGeneral(String titulo) {
        super();
        this.setSize(800, 481);
        this.setResizable(false);
        this.setTitle(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame = this;
    }

    public void addImagenDeFondo(String direccion) {
        ImageIcon iconoNuevaPartida = new ImageIcon(direccion);
        JLabel icono = new JLabel(iconoNuevaPartida);
        icono.setBounds(0, 0, 800, 481);
        this.getContentPane().add(icono);
    }
}
