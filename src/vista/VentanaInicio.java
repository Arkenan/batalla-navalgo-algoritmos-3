package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaInicio extends JFrame implements ItemListener {
    JButton btnIniciarPartida, btnCargarPartida, btnJugar;
    JComboBox<String> menu;
    JFileChooser fc;
    JFrame frame;
    int tamX = 218; // largo de los botones
    int tamY = 54; // ancho de los botones

    public VentanaInicio() {
        setSize(800, 481);
        this.setTitle("Batalla Navalgo");
        btnIniciarPartida = this.addBtnNuevaPartida();
        btnCargarPartida = this.addBtnGargarPartida();
        btnJugar = this.addBtnJugar();
        menu = this.addMenuDespegable();
        this.addImagenDeFondo();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame = this;

    }

    public static void main(String[] args) {
        VentanaInicio ventana = new VentanaInicio();
        ventana.setVisible(true);
    }

    private void addImagenDeFondo() {
        ImageIcon iconoNuevaPartida = new ImageIcon("imagenes/inicio/a.jpg");
        JLabel icono = new JLabel(iconoNuevaPartida);
        icono.setBounds(10, 40, 50, 100);
        this.getContentPane().add(icono);
    }

    private JButton addBtnNuevaPartida() {
        ImageIcon icono = new ImageIcon("imagenes/inicio/nuevaPartida.png");
        JButton btnNuevaPartida = new JButton(icono);
        btnNuevaPartida.setBounds(42, 100, tamX, tamY);
        btnNuevaPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                btnCargarPartida.setVisible(false);
                menu.setVisible(true);
            }
        });
        this.getContentPane().add(btnNuevaPartida);
        return btnNuevaPartida;
    }

    private JButton addBtnGargarPartida() {
        ImageIcon icono = new ImageIcon("imagenes/inicio/guardarPartida.png");
        JButton btnGuardarPartida = new JButton(icono);
        btnGuardarPartida.setBounds(41, 200, tamX, tamY);
        btnGuardarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos XML", "xml");
                fileChooser.setFileFilter(filtro);
                int seleccion = fileChooser.showOpenDialog(null);

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
        btnJugar.setOpaque(false);
        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.setVisible(false);
                VentanaJuego ventana = new VentanaJuego(menu.getSelectedItem().toString());
                ventana.setVisible(true);
            }
        });
        this.getContentPane().add(btnJugar);
        return btnJugar;
    }
}
