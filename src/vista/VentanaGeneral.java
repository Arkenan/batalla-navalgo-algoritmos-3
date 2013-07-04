package vista;

import javax.swing.JFrame;

public class VentanaGeneral extends JFrame {

    public VentanaGeneral(String titulo) {
        super();
        this.setSize(800, 481);
        this.setResizable(false);
        this.setTitle(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
