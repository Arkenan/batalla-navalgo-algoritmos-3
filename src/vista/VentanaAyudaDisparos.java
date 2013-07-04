package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaAyudaDisparos extends JFrame {

    ManejadorVentanas manejador;
    int tamX = 218; // ancho de los botones
    int tamY = 54; // altura de los botones
    int posY = 360; // posicion en Y de los botones
    JFrame frame;

    public VentanaAyudaDisparos(ManejadorVentanas manejadorV) {
        manejador = manejadorV;
        setSize(800, 481);
        this.setTitle("Batalla Navalgo - Ayuda Disparos");
        this.addBtnAtras();
        this.addBtnSig();
        this.addImagenDeFondo();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame = this;
    }

    private JButton addBtnAtras() {
        ImageIcon icono = new ImageIcon("imagenes/ayuda/disparos/barcos.png");
        JButton btnAtras = new JButton(icono);
        btnAtras.setBounds(0, posY, tamX, tamY);
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manejador.abrirAyudaBarcos(frame);
            }
        });
        this.getContentPane().add(btnAtras);
        return btnAtras;
    }

    private JButton addBtnSig() {
        ImageIcon icono = new ImageIcon("imagenes/ayuda/disparos/inicio.png");
        JButton btnSiguiente = new JButton(icono);
        btnSiguiente.setBounds(550, posY, tamX, tamY);
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manejador.abrirInicio(frame);
            }
        });
        this.getContentPane().add(btnSiguiente);
        return btnSiguiente;

    }

    private void addImagenDeFondo() {
        ImageIcon iconoNuevaPartida = new ImageIcon("imagenes/ayuda/disparos/ayuda.png");
        JLabel icono = new JLabel(iconoNuevaPartida);
        icono.setBounds(0, 0, 800, 481);
        this.getContentPane().add(icono);

    }
}
