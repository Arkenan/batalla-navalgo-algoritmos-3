package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaAyudaDisparos extends VentanaGeneral {

    ManejadorVentanas manejador;
    // int tamX = 218; // ancho de los botones
    // int tamY = 54; // altura de los botones
    int posY = 360; // posicion en Y de los botones

    public VentanaAyudaDisparos(ManejadorVentanas manejadorV) {
        super("Batalla Navalgo - Ayuda Disparos");
        manejador = manejadorV;
        this.addBtnAtras();
        this.addBtnSig();
        this.addImagenDeFondo("imagenes/ayuda/disparos/ayuda.png");
    }

    private JButton addBtnAtras() {
        // ImageIcon icono = new ImageIcon("imagenes/ayuda/disparos/barcos.png");
        // JButton btnAtras = new JButton(icono);
        // btnAtras.setBounds(0, posY, tamX, tamY);
        Boton btnAtras = new Boton("imagenes/ayuda/disparos/barcos.png", 0, posY);
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
        // ImageIcon icono = new ImageIcon("imagenes/ayuda/disparos/inicio.png");
        // JButton btnSiguiente = new JButton(icono);
        // btnSiguiente.setBounds(550, posY, tamX, tamY);
        Boton btnSiguiente = new Boton("imagenes/ayuda/disparos/inicio.png", 550, posY);

        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manejador.abrirInicio(frame);
            }
        });
        this.getContentPane().add(btnSiguiente);
        return btnSiguiente;

    }

    /*
     * private void addImagenDeFondo() { ImageIcon iconoNuevaPartida = new
     * ImageIcon("imagenes/ayuda/disparos/ayuda.png"); JLabel icono = new JLabel(iconoNuevaPartida);
     * icono.setBounds(0, 0, 800, 481); this.getContentPane().add(icono);
     * 
     * }
     */
}
