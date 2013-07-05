package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VentanaAyudaBarcos extends VentanaGeneral {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    int posTextoX = 400; // posicion en X de los textos ayuda
    ManejadorVentanas manejador;
    int posY = 355; // posicion en Y de los botones

    public VentanaAyudaBarcos(ManejadorVentanas manejadorV) {
        super("Batalla Navalgo - Ayuda");
        this.addBtnAtras();
        this.addBtnSig();
        this.addImagenDeFondo("imagenes/ayuda/barcos/ayuda.png");
        manejador = manejadorV;

    }

    private JButton addBtnAtras() {
        Boton btnAtras = new Boton("imagenes/ayuda/barcos/intro.png", 0, posY);
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manejador.abrirTutorial(frame);
            }
        });
        this.getContentPane().add(btnAtras);
        return btnAtras;
    }

    private JButton addBtnSig() {
        Boton btnSiguiente = new Boton("imagenes/ayuda/barcos/disparos.png", 550, posY);
        btnSiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manejador.abrirAyudaDisparos(frame);
            }
        });
        this.getContentPane().add(btnSiguiente);
        return btnSiguiente;

    }
}
