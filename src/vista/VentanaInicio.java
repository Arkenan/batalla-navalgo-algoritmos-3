package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaInicio extends VentanaGeneral implements ItemListener {
    JButton btnIniciarPartida, btnCargarPartida, btnJugar, btnTutorial, btnAtras;
    JComboBox<String> menu;
    JFileChooser fc;
    VentanaInicio frame;
    ManejadorVentanas manejador;
    int tamX = 218; // largo de los botones
    int tamY = 54; // ancho de los botones

    public VentanaInicio(ManejadorVentanas manejadorVentana) {
        super("Batalla Navalgo");
        btnAtras = this.addBtnAtras();
        btnIniciarPartida = this.addBtnNuevaPartida();
        btnCargarPartida = this.addBtnGargarPartida();
        btnTutorial = this.addBtnTutorial();
        btnJugar = this.addBtnJugar();
        menu = this.addMenuDespegable();
        this.addImagenDeFondo("imagenes/inicio/a.jpg");
        frame = this; // no cambiar
        manejador = manejadorVentana;

    }

    /*
     * public static void main(String[] args) { VentanaInicio ventana = new VentanaInicio();
     * ventana.setVisible(true); }
     */

    private JButton addBtnAtras() {
        // ImageIcon icono = new ImageIcon("imagenes/inicio/atras.png");
        // JButton btnAtras = new JButton(icono);
        // btnAtras.setBounds(38, 400, tamX, tamY);
        Boton btnAtras = new Boton("imagenes/inicio/atras.png", 38, 400);

        btnAtras.setVisible(false);
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                btnCargarPartida.setVisible(true);
                btnTutorial.setVisible(true);
                btnJugar.setVisible(false);
                menu.setVisible(false);
                frame.sacarBtnAtras();
            }
        });
        this.getContentPane().add(btnAtras);
        return btnAtras;
    }

    /*
     * private void addImagenDeFondo() { ImageIcon iconoNuevaPartida = new
     * ImageIcon("imagenes/inicio/a.jpg"); JLabel icono = new JLabel(iconoNuevaPartida);
     * icono.setBounds(10, 40, 50, 100); this.getContentPane().add(icono); }
     */

    private JButton addBtnNuevaPartida() {
        // ImageIcon icono = new ImageIcon("imagenes/inicio/nuevaPartida.png");
        // JButton btnNuevaPartida = new JButton(icono);
        // btnNuevaPartida.setBounds(42, 100, tamX, tamY);
        Boton btnNuevaPartida = new Boton("imagenes/inicio/nuevaPartida.png", 42, 100);

        btnNuevaPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                btnCargarPartida.setVisible(false);
                btnTutorial.setVisible(false);
                menu.setVisible(true);
                btnAtras.setVisible(true);
            }
        });
        this.getContentPane().add(btnNuevaPartida);
        return btnNuevaPartida;
    }

    private JButton addBtnGargarPartida() {
        // ImageIcon icono = new ImageIcon("imagenes/inicio/guardarPartida.png");
        // JButton btnGuardarPartida = new JButton(icono);
        // btnGuardarPartida.setBounds(41, 200, tamX, tamY);
        Boton btnGuardarPartida = new Boton("imagenes/inicio/guardarPartida.png", 41, 200);
        btnGuardarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos XML", "xml");
                fileChooser.setFileFilter(filtro);
                int seleccion = fileChooser.showOpenDialog(null);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File fichero = fileChooser.getSelectedFile();
                    manejador.abrirJuego((fichero.getName().split("\\.")[0]));
                }

            }
        });
        this.getContentPane().add(btnGuardarPartida);
        return btnGuardarPartida;
    }

    private JComboBox<String> addMenuDespegable() {
        JComboBox<String> menu = new JComboBox<String>();
        menu.setBounds(90, 170, 100, 40);
        menu.setVisible(false);
        menu.addItemListener(this);

        menu.addItem("--seleccionar--");
        menu.addItem("facil");
        menu.addItem("medio");
        menu.addItem("dificil");
        this.getContentPane().add(menu);
        return menu;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == menu) {
            if (!menu.getSelectedItem().equals("--seleccionar--")) {
                btnJugar.setVisible(true);
            } else {
                btnJugar.setVisible(false);
            }
        }
    }

    private JButton addBtnJugar() {
        ImageIcon icono = new ImageIcon("imagenes/inicio/jugar.png");
        JButton btnJugar = new JButton(icono);
        btnJugar.setBounds(100, 300, 90, 90);
        btnJugar.setVisible(false);
        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manejador.abrirJuego(menu.getSelectedItem().toString());
            }
        });
        this.getContentPane().add(btnJugar);
        return btnJugar;
    }

    private JButton addBtnTutorial() {
        // ImageIcon icono = new ImageIcon("imagenes/inicio/tutorial.png");
        // JButton btnTutorial = new JButton(icono);
        // btnTutorial.setBounds(41, 300, tamX, tamY);
        Boton btnTutorial = new Boton("imagenes/inicio/tutorial.png", 41, 300);
        btnTutorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                manejador.abrirTutorial(frame);
            }
        });
        this.getContentPane().add(btnTutorial);
        return btnTutorial;
    }

    private void sacarBtnAtras() {
        btnAtras.setVisible(false);
    }
}