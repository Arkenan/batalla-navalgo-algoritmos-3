package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaAyudaBarcos extends VentanaGeneral {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JButton btnAyudaBarcos, btnAyudaDisparos;
    int posTextoX = 400; // posicion en X de los textos ayuda
    ManejadorVentanas manejador;
    int tamX = 218; // ancho de los botones
    int tamY = 54; // altura de los botones
    int posY = 355; // posicion en Y de los botones
    JFrame frame;

    public VentanaAyudaBarcos(ManejadorVentanas manejadorV) {
        super("Batalla Navalgo - Ayuda");
        this.addBtnAtras();
        this.addBtnSig();
        this.addAyudaBuque();
        this.addAyudaDestructor();
        this.addAyudaLancha();
        this.addAyudaPortaaviones();
        this.addAyudaRompehielo();
        this.addImagenDeFondo();
        manejador = manejadorV;
        frame = this;
    }

    private void addImagenDeFondo() {
        ImageIcon iconoNuevaPartida = new ImageIcon("imagenes/ayuda/barcos/ayuda.png");
        JLabel icono = new JLabel(iconoNuevaPartida);
        icono.setBounds(0, 0, 800, 481);
        this.getContentPane().add(icono);

    }

    private void addAyudaBuque() {
        this.addTextoBuque();
        this.addImageBuque();
    }

    private void addImageBuque() {
        ImageIcon icono = new ImageIcon("imagenes/ayuda/barcos/buque.png");
        JLabel iconoBuque = new JLabel(icono);
        iconoBuque.setOpaque(false);
        iconoBuque.setBounds(50, 5, 294, 76);
        this.getContentPane().add(iconoBuque);
    }

    private void addTextoBuque() {
        Texto nombre = new Texto("BUQUE", 400, 5);
        Texto tamanio = new Texto("Nave 4 Casillas", 400, 15);
        Texto disparo = new Texto("Disparo : SI", 400, 25);
        Texto minaContacto = new Texto("Mina Contacto : SI", 400, 35);
        Texto minasRadios = new Texto("Minas Radio 1,2 :SI", 400, 45);
        Texto vida = new Texto("Vida : 1", 520, 5);
        JLabel infoExtra = new JLabel("un impacto la destruye por completo");
        infoExtra.setBounds(520, 15, 300, 30);
        infoExtra.setForeground(Color.BLACK);
        Texto[] lista = {nombre, tamanio, disparo, minaContacto, minasRadios, vida};
        this.addTextoAlPanel(lista);

    }

    private void addAyudaLancha() {
        this.addTextoLancha();
        this.addImageLancha();
    }

    private void addTextoLancha() {
        Texto nombre = new Texto("LANCHA", 400, 355);
        Texto tamanio = new Texto("Nave 2 Casillas", 400, 365);
        Texto disparo = new Texto("Disparo : SI", 400, 375);
        Texto minaContacto = new Texto("Mina Contacto : SI", 400, 385);
        Texto minasRadios = new Texto("Minas Radio 1,2 :SI", 400, 395);
        Texto vida = new Texto("Vida : 1", 520, 355);
        Texto[] lista = {nombre, tamanio, disparo, minaContacto, minasRadios, vida};
        this.addTextoAlPanel(lista);

    }

    private void addImageLancha() {
        ImageIcon icono = new ImageIcon("imagenes/ayuda/barcos/lancha.png");
        JLabel iconoBuque = new JLabel(icono);
        iconoBuque.setOpaque(false);
        iconoBuque.setBounds(150, 355, 294, 76);
        this.getContentPane().add(iconoBuque);
    }

    private void addAyudaPortaaviones() {
        this.addTextoPortaaviones();
        this.addImagePortaaviones();
    }

    private void addImagePortaaviones() {
        ImageIcon icono = new ImageIcon("imagenes/ayuda/barcos/portaaviones.png");
        JLabel iconoJ = new JLabel(icono);
        iconoJ.setOpaque(false);
        iconoJ.setBounds(60, 270, 387, 82);
        this.getContentPane().add(iconoJ);
    }

    private void addTextoPortaaviones() {
        int posX = 450;
        Texto nombre = new Texto("PORTAAVIONES", posX, 270);
        Texto tamanio = new Texto("Nave 5 Casillas", posX, 285);
        Texto disparo = new Texto("Disparo : SI", posX, 295);
        Texto minaContacto = new Texto("Mina Contacto : SI", posX, 305);
        Texto minasRadios = new Texto("Minas Radio 1,2 :SI", posX, 315);
        Texto vida = new Texto("Vida : 1", 570, 270);
        this.getContentPane().add(vida);
        Texto[] lista = {nombre, tamanio, disparo, minaContacto, minasRadios, vida};
        this.addTextoAlPanel(lista);

    }

    private void addAyudaDestructor() {
        this.addTextoDestructor();
        this.addImageDestructor();
    }

    private void addTextoDestructor() {
        Texto nombre = new Texto("DESTRUCTOR", 400, 180);
        Texto tamanio = new Texto("Nave 3 Casillas", 400, 190);
        Texto disparo = new Texto("Disparo : SI", 400, 200);
        Texto minaContacto = new Texto("Mina Contacto : NO", 400, 210);
        Texto minasRadios = new Texto("Minas Radio 1,2 :NO", 400, 220);
        Texto vida = new Texto("Vida : 1", 520, 180);
        Texto[] lista = {nombre, tamanio, disparo, minaContacto, minasRadios, vida};
        this.addTextoAlPanel(lista);
    }

    private void addImageDestructor() {
        ImageIcon icono = new ImageIcon("imagenes/ayuda/barcos/destructor.png");
        JLabel iconoJ = new JLabel(icono);
        iconoJ.setOpaque(false);
        iconoJ.setBounds(60, 180, 261, 84);
        this.getContentPane().add(iconoJ);
    }

    private void addAyudaRompehielo() {
        this.addTextoRompehielo();
        this.addImageRompehielo();
    }

    private void addTextoRompehielo() {
        Texto nombre = new Texto("ROMPEHIELOS", 400, 90);
        Texto tamanio = new Texto("Nave 3 Casillas", 400, 100);
        Texto disparo = new Texto("Disparo : SI", 400, 110);
        Texto minaContacto = new Texto("Mina Contacto : SI", 400, 120);
        Texto minasRadios = new Texto("Minas Radio 1,2 :SI", 400, 130);
        Texto vida = new Texto("Vida : 2", 520, 90);
        Texto[] lista = {nombre, tamanio, disparo, minaContacto, minasRadios, vida};
        this.addTextoAlPanel(lista);
    }

    private void addImageRompehielo() {
        ImageIcon icono = new ImageIcon("imagenes/ayuda/barcos/rompehielo.png");
        JLabel iconoJ = new JLabel(icono);
        iconoJ.setOpaque(false);
        iconoJ.setBounds(60, 90, 294, 76);
        this.getContentPane().add(iconoJ);
    }

    private void addTextoAlPanel(JLabel[] lista) {
        for (int i = 0; i < lista.length; i++) {
            JLabel texto = lista[i];
            this.getContentPane().add(texto);
        }
    }

    private JButton addBtnAtras() {
        ImageIcon icono = new ImageIcon("imagenes/ayuda/barcos/intro.png");
        JButton btnAtras = new JButton(icono);
        btnAtras.setBounds(0, posY, tamX, tamY);
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
        ImageIcon icono = new ImageIcon("imagenes/ayuda/barcos/disparos.png");
        JButton btnSiguiente = new JButton(icono);
        btnSiguiente.setBounds(550, posY, tamX, tamY);
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
