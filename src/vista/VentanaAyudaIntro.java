package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaAyudaIntro extends VentanaGeneral {
    JButton btnAtras, btnSig;
    JFrame siguiente, anterior, frame;
    ManejadorVentanas manejador;
    int tamX = 218;
    int tamY = 54;
    int posY = 355;

    public VentanaAyudaIntro(ManejadorVentanas manejadorV) {
        super("Ayuda - Intro");
        siguiente = new VentanaAyudaBarcos(manejadorV);
        btnAtras = this.addBtnAtras();
        btnSig = this.addBtnSig();
        this.addImagenDeFondo();
        manejador = manejadorV;
        frame = this;
    }

    private void addImagenDeFondo() {
        ImageIcon iconoNuevaPartida = new ImageIcon("imagenes/ayuda/intro/ayuda.png");
        JLabel icono = new JLabel(iconoNuevaPartida);
        icono.setBounds(0, 0, 800, 481);
        this.getContentPane().add(icono);

    }

    private JButton addBtnSig() {
        ImageIcon icono = new ImageIcon("imagenes/ayuda/intro/barcos.png");
        JButton btnSiguiente = new JButton(icono);
        btnSiguiente.setBounds(524, posY, tamX, tamY);
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
        ImageIcon icono = new ImageIcon("imagenes/ayuda/intro/inicio.png");
        JButton btnAtras = new JButton(icono);
        btnAtras.setBounds(42, posY, tamX, tamY);
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
