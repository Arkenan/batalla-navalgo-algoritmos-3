package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaInicio extends VentanaGeneral implements ItemListener {
    JButton btnIniciarPartida, btnCargarPartida, btnJugar, btnTutorial, btnAtras;
    JComboBox<String> menu;
    JFileChooser fc;
    JLabel texto;
    VentanaInicio frame;
    ManejadorVentanas manejador;
    int tamX = 218; // largo de los botones
    int tamY = 54; // ancho de los botones

    public VentanaInicio(ManejadorVentanas manejadorVentana) {
        super("Batalla Navalgo");
        btnAtras = this.addBtnAtras();
        btnIniciarPartida = this.addBtnNuevaPartida();
        btnCargarPartida = this.addBtnCargarPartida();
        btnTutorial = this.addBtnTutorial();
        btnJugar = this.addBtnJugar();
        menu = this.addMenuDespegable();
        texto = this.addTextoAyuda();
        this.addImagenDeFondo("imagenes/inicio/a.jpg");
        frame = this; // no cambiar
        manejador = manejadorVentana;

    }

    private JButton addBtnAtras() {
        Boton btnAtras = new Boton("imagenes/inicio/atras.png", 38, 400);
        btnAtras.setVisible(false);
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                btnCargarPartida.setVisible(true);
                btnTutorial.setVisible(true);
                btnJugar.setVisible(false);
                menu.setVisible(false);
                sacarBtnAtras();
            }
        });
        this.getContentPane().add(btnAtras);
        return btnAtras;
    }

    private JButton addBtnNuevaPartida() {
        Boton btnNuevaPartida = new Boton("imagenes/inicio/nuevaPartida.png", 42, 100);
        btnNuevaPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                btnCargarPartida.setVisible(false);
                btnTutorial.setVisible(false);
                menu.setVisible(true); // medio
                btnAtras.setVisible(true); // inferior izquierda
                texto.setVisible(true); // medio
                sacarBtnIniciar();
            }
        });
        this.getContentPane().add(btnNuevaPartida);
        return btnNuevaPartida;
    }

    private JButton addBtnCargarPartida() {
        Boton btnGuardarPartida = new Boton("imagenes/inicio/guardarPartida.png", 41, 200);
        btnGuardarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos XML", "xml");
                fileChooser.setCurrentDirectory(new File("Niveles XML"));
                fileChooser.setFileFilter(filtro);
                int seleccion = fileChooser.showOpenDialog(null);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File fichero = fileChooser.getSelectedFile();
                    String dirArchivo;
                    try {
                        dirArchivo = fichero.getCanonicalPath();
                    } catch (IOException e) {
                        dirArchivo = "";
                    }
                    if (!(new File(dirArchivo).exists())) {
                        JOptionPane.showMessageDialog(null, "Archivo no encontrado: " + dirArchivo);
                    } else {
                        manejador.abrirJuego("file:///" + dirArchivo);
                    }
                }
            }
        });
        this.getContentPane().add(btnGuardarPartida);
        return btnGuardarPartida;
    }

    private JComboBox<String> addMenuDespegable() {
        JComboBox<String> menu = new JComboBox<String>();

        menu.setBounds(90, 170, 150, 40); // mover la barra al medio
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
                btnJugar.setVisible(true); // medio abajo del menu
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
                manejador.abrirJuego("Niveles XML/" + menu.getSelectedItem().toString() + ".xml");
            }
        });
        this.getContentPane().add(btnJugar);
        return btnJugar;
    }

    private JButton addBtnTutorial() {
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

    private JLabel addTextoAyuda() {
        ImageIcon imagen = new ImageIcon("Imagenes/inicio/textoAyuda.png");
        JLabel icono = new JLabel(imagen);
        icono.setBounds(332, 58, 0, 0); // donde estan los ceros pone la posicion (centro arriba de
                                        // todo)
        frame.getContentPane().add(icono);
        return icono;
    }

    private void sacarBtnAtras() {
        btnAtras.setVisible(false);
    }

    private void sacarBtnIniciar() {
        btnIniciarPartida.setVisible(false);
    }
}