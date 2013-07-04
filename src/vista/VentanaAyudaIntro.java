package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaAyudaIntro extends VentanaGeneral {
    JButton btnAtras, btnSig;
    JFrame siguiente, anterior;
    ManejadorVentanas manejador;
    int tamX = 218;
    int tamY = 54;
    int posY = 355;

    public VentanaAyudaIntro(ManejadorVentanas manejadorV) {
        super("Ayuda - Intro");
        siguiente = new VentanaAyudaBarcos(manejadorV);
        btnAtras = this.addBtnAtras();
        btnSig = this.addBtnSig();
        this.addImagenDeFondo("imagenes/ayuda/intro/ayuda.png");
        manejador = manejadorV;
    }

    /*
     * private void addImagenDeFondo() { ImageIcon iconoNuevaPartida = new
     * ImageIcon("imagenes/ayuda/intro/ayuda.png"); JLabel icono = new JLabel(iconoNuevaPartida);
     * icono.setBounds(0, 0, 800, 481); this.getContentPane().add(icono);
     * 
     * }
     */

    private JButton addBtnSig() {
        // ImageIcon icono = new ImageIcon("imagenes/ayuda/intro/barcos.png");
        // JButton btnSiguiente = new JButton(icono);
        // btnSiguiente.setBounds(524, posY, tamX, tamY);
        Boton btnSiguiente = new Boton("imagenes/ayuda/intro/barcos.png", 524, posY);
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manejador.abrirAyudaBarcos(frame);
            }
        });
        this.getContentPane().add(btnSiguiente);
        return btnSiguiente;

    }

    private JButton addBtnAtras() {
        // ImageIcon icono = new ImageIcon("imagenes/ayuda/intro/inicio.png");
        // JButton btnAtras = new JButton(icono);
        // btnAtras.setBounds(42, posY, tamX, tamY);
        Boton btnAtras = new Boton("imagenes/ayuda/intro/inicio.png", 42, posY);

        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manejador.abrirInicio(frame);
            }
        });
        this.getContentPane().add(btnAtras);
        return btnAtras;
    }
}
