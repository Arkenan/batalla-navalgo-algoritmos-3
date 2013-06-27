package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import partes.Parte;
import partida.Partida;
import vistasbarcos.VistaBarcos;
import barcos.Barco;
import barcos.Vector;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.GameLoop;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class VentanaPrincipal implements MouseListener {

    private JFrame frame;
    private GameLoop gameLoop;
    private Partida partida;
    private Vector ultimaPosicionClickeada;
    JLabel puntaje;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    VentanaPrincipal window = new VentanaPrincipal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public VentanaPrincipal() {
        try {
            initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the contents of the frame.
     * 
     * @throws IOException
     */
    private void initialize() throws IOException {
        frame = new JFrame();
        frame.setForeground(new Color(0, 0, 0));
        frame.setBounds(100, 100, 800, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Batalla Naval - Grupo 11");

        JButton btnIniciar = this.addBotonIniciar();

        JButton btnDetener = this.addBotonDetener();

        JButton btnPasarTurno = this.addBotonPasarTurno();

        JButton btnDisparar = this.addBotonDisparar();

        JButton btnMina = this.addBotonMina();

        JButton btnMina1 = this.addBotonMina1();

        JButton btnMina2 = this.addBotonMina2();

        JPanel panel = this.addSuperficiePanel();

        this.gameLoop = new GameLoop((SuperficieDeDibujo) panel);

        this.inicializarModelo();

        puntaje = this.addCosaPrueba();

        this.addMouseListener(panel);

        this.addKeyListener();

        this.setComponentsFocus(btnIniciar, btnDetener);
    }

    private JLabel addCosaPrueba() {
        int puntaje = partida.getPuntos();
        JLabel coso = new JLabel("Puntaje:" + Integer.toString(puntaje));
        coso.setBounds(200, 15, 200, 50);
        frame.add(coso);
        return coso;
    }

    private JButton addBotonMina2() {
        ImageIcon iconoMinaRadio2 = new ImageIcon("mina_radio2.png");
        JButton btnMina2 = new JButton("Mina Radio 2", iconoMinaRadio2);
        btnMina2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                partida.colocarDisparo("minadobleradio", ultimaPosicionClickeada);
                puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));

            }
        });
        btnMina2.setBounds(550, 350, 150, 100);
        frame.getContentPane().add(btnMina2);
        return btnMina2;
    }

    private JButton addBotonMina1() {
        ImageIcon iconoMinaRadio1 = new ImageIcon("mina_radio1.png");
        JButton btnMina1 = new JButton("Mina Radio 1", iconoMinaRadio1);
        btnMina1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                partida.colocarDisparo("minaradio", ultimaPosicionClickeada);
                puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
            }
        });
        btnMina1.setBounds(550, 250, 150, 100);
        frame.getContentPane().add(btnMina1);
        return btnMina1;
    }

    private JButton addBotonMina() {
        ImageIcon iconoMinaRadio = new ImageIcon("mina_radio.png");
        JButton btnMina = new JButton("Mina Contacto", iconoMinaRadio);
        btnMina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                partida.colocarDisparo("minacontacto", ultimaPosicionClickeada);
                puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
            }
        });
        btnMina.setBounds(550, 150, 150, 100);
        frame.getContentPane().add(btnMina);
        return btnMina;
    }

    private JButton addBotonDisparar() {
        ImageIcon iconoDisparar = new ImageIcon("disparo.png");
        JButton btnDisparar = new JButton("Disparar", iconoDisparar);
        btnDisparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                partida.colocarDisparo("disparoconvencional", ultimaPosicionClickeada);
                puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
            }
        });
        btnDisparar.setBounds(550, 50, 150, 100);
        frame.getContentPane().add(btnDisparar);
        return btnDisparar;
    }

    private void inicializarModelo() {
        ArrayList<Color> Lista = new ArrayList<Color>();
        Lista.add(Color.BLUE);
        Lista.add(Color.GREEN);
        Lista.add(Color.YELLOW);
        Lista.add(Color.ORANGE);
        ultimaPosicionClickeada = new Vector(0, 0);
        partida = new Partida();
        ArrayList<Barco> barcos = partida.crearBarcosPorDefault();
        Parte parte;
        ArrayList<Parte> partes;
        for (int i = 0; i < barcos.size(); i++) {
            partes = barcos.get(i).obtenerPartes();
            int posicion = (int) (Math.random() * Lista.size());
            Color definido = Lista.get(posicion);
            for (int j = 0; j < partes.size(); j++) {
                parte = partes.get(j);
                VistaBarcos vistaBarco = new VistaBarcos(40, parte);
                vistaBarco.setColor(definido);
                this.gameLoop.agregar(vistaBarco);
            }
        }
    }

    private void setComponentsFocus(JButton btnIniciar, JButton btnDetener) {
        frame.setFocusable(true);
        btnDetener.setFocusable(false);
        btnIniciar.setFocusable(false);
    }

    private void addKeyListener() {
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent arg0) {
                System.out.println("Key pressed");
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
            }

            @Override
            public void keyPressed(KeyEvent arg0) {
                System.out.println("Ping");
            }

        });
    }

    private void addMouseListener(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
            }
        });
    }

    private JPanel addSuperficiePanel() {
        JPanel panel = new SuperficiePanel();
        panel.setBackground(Color.BLACK);
        panel.addMouseListener(this);
        panel.setBounds(150, 50, 400, 400);
        frame.getContentPane().add(panel);
        return panel;
    }

    private JButton addBotonDetener() {
        ImageIcon iconoCerrar = new ImageIcon("cerrar.png");
        JButton btnDetener = new JButton("Detener", iconoCerrar);
        btnDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLoop.detenerEjecucion();
            }
        });
        btnDetener.setBounds(0, 250, 150, 100);
        frame.getContentPane().add(btnDetener);
        return btnDetener;
    }

    private JButton addBotonPasarTurno() {
        ImageIcon iconoPasarTurno = new ImageIcon("pasar_turno.png");
        JButton btnPasarTurno = new JButton("Pasar Turno", iconoPasarTurno);
        btnPasarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                partida.pasarTurno();
                puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
            }
        });
        btnPasarTurno.setBounds(0, 150, 150, 100);
        frame.getContentPane().add(btnPasarTurno);
        return btnPasarTurno;
    }

    private JButton addBotonIniciar() {
        ImageIcon iconoIniciar = new ImageIcon("iniciar.png");
        JButton btnIniciar = new JButton("Iniciar", iconoIniciar);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameLoop.iniciarEjecucion();
            }
        });
        btnIniciar.setBounds(0, 50, 150, 100);
        frame.getContentPane().add(btnIniciar);
        return btnIniciar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ultimaPosicionClickeada.setX((e.getX() / 40) + 1);
        ultimaPosicionClickeada.setY((e.getY() / 40) + 1);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
