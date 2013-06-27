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

import partida.Partida;
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
    JLabel posicionClickeada;

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

        int tamX = 250;

        int tamY = 50;

        int posicion = 0;

        int posicionY = 50;

        JButton btnIniciar = this.addBotonIniciar(posicion, posicionY, tamX, tamY);

        posicionY += tamY;

        JButton btnDetener = this.addBotonDetener(posicion, posicionY, tamX, tamY);

        posicionY += tamY;

        JButton btnPasarTurno = this.addBotonPasarTurno(posicion, posicionY, tamX, tamY);

        int posicionPanelX = posicion + tamX;

        int posicionPanelY = 50;

        int tamPanelX = 400;

        int tamPanelY = 400;

        JPanel panel = this.addSuperficiePanel(posicionPanelX, posicionPanelY, tamPanelX, tamPanelY);

        int posicionX = posicionPanelX + tamPanelX;

        int posicionY1 = 50;

        JButton btnDisparar = this.addBotonDisparar(posicionX, posicionY1, tamX, tamY);

        posicionY1 += tamY;

        JButton btnMina = this.addBotonMina(posicionX, posicionY1, tamX, tamY);

        posicionY1 += tamY;
        JButton btnMina1 = this.addBotonMina1(posicionX, posicionY1, tamX, tamY);

        posicionY1 += tamY;
        JButton btnMina2 = this.addBotonMina2(posicionX, posicionY1, tamX, tamY);

        this.addAyudaBuque();

        this.gameLoop = new GameLoop((SuperficieDeDibujo) panel);

        this.inicializarModelo();

        puntaje = this.addCosaPrueba();

        posicionClickeada = this.addCosaPrueba2();

        this.addMouseListener(panel);

        this.addKeyListener();

        this.setComponentsFocus(btnIniciar, btnDetener);

    }

    private JLabel addCosaPrueba() {
        int puntaje = partida.getPuntos();
        JLabel coso = new JLabel("Puntaje:" + Integer.toString(puntaje));
        coso.setBounds(200, 10, 200, 20);
        frame.add(coso);
        return coso;

    }

    private void addAyudaBuque() {
        ImageIcon icono = new ImageIcon("imagenes/lancha/lancha.png");
        JLabel iconoBuque = new JLabel(icono);
        // JLabel vida = new JLabel("Vida:1");
        // JLabel factores = new JLabel("Factores que lo dañan");
        // JLabel minaContacto = new JLabel("Mina Contacto : SI");
        // JLabel minaRadio = new JLabel("Mina Radio : SI");
        // JLabel disparo = new JLabel("Disparo : SI");
        iconoBuque.setBounds(0, 500, 500, 200);
        frame.getContentPane().add(iconoBuque);
        // frame.getContentPane().add(vida);
        // frame.getContentPane().add(factores);
        // frame.getContentPane().add(minaContacto);
        // frame.getContentPane().add(minaRadio);
        // frame.getContentPane().add(disparo);
    }

    private JLabel addCosaPrueba2() {
        JLabel coso = new JLabel("Posicion a afectar: (" + Integer.toString(ultimaPosicionClickeada.x()) + "," + Integer.toString(ultimaPosicionClickeada.y()) + ")");
        coso.setBounds(300, 10, 200, 20);
        frame.add(coso);
        return coso;
    }

    private JButton addBotonMina2(int posicionX, int posicionY, int tamX, int tamY) {
        ImageIcon iconoMinaRadio2 = new ImageIcon("imagenes/iconos/mina_radio2.png");
        JButton btnMina2 = new JButton("Mina Radio 2", iconoMinaRadio2);
        btnMina2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                partida.colocarDisparo("minadobleradio", ultimaPosicionClickeada);
                puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
                verificarFinDelJuego();

            }
        });
        btnMina2.setBounds(posicionX, posicionY, tamX, tamY);
        frame.getContentPane().add(btnMina2);
        return btnMina2;
    }

    private JButton addBotonMina1(int posicionX, int posicionY, int tamX, int tamY) {
        ImageIcon iconoMinaRadio1 = new ImageIcon("imagenes/iconos/mina_radio1.png");
        JButton btnMina1 = new JButton("Mina Radio 1", iconoMinaRadio1);
        btnMina1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                partida.colocarDisparo("minaradio", ultimaPosicionClickeada);
                puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
                verificarFinDelJuego();
            }
        });
        btnMina1.setBounds(posicionX, posicionY, tamX, tamY);
        frame.getContentPane().add(btnMina1);
        return btnMina1;
    }

    private JButton addBotonMina(int posicionX, int posicionY, int tamX, int tamY) {
        ImageIcon iconoMinaRadio = new ImageIcon("imagenes/iconos/mina_contacto.png");
        JButton btnMina = new JButton("Mina Contacto", iconoMinaRadio);
        btnMina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                partida.colocarDisparo("minacontacto", ultimaPosicionClickeada);
                puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
                verificarFinDelJuego();
            }
        });

        btnMina.setBounds(posicionX, posicionY, tamX, tamY);
        frame.getContentPane().add(btnMina);

        return btnMina;
    }

    private JButton addBotonDisparar(int posicionX, int posicionY, int tamX, int tamY) {
        ImageIcon icono = new ImageIcon("imagenes/iconos/disparo.png");
        JButton btnDisparar = new JButton("Disparar", icono);
        btnDisparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                partida.colocarDisparo("disparoconvencional", ultimaPosicionClickeada);
                puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
                verificarFinDelJuego();
            }
        });
        btnDisparar.setBounds(posicionX, posicionY, tamX, tamY);
        frame.getContentPane().add(btnDisparar);
        return btnDisparar;
    }

    private void inicializarModelo() throws IOException {
        /*
         * PARA NIVELES ArrayList<Disparo> disparos = new ArrayList<Disparo>(); Element nodoPartida
         * = Partida.obtenerNodoPartida("niveles XML/pruebas.xml"); partida = new
         * Partida(nodoPartida); ArrayList<Barco> barcos = partida.crearBarcos(nodoPartida);
         */

        ultimaPosicionClickeada = new Vector(0, 0);
        partida = new Partida();

        ArrayList<Barco> barcos = partida.crearBarcosPorDefault();
        ArrayList<VistaParte> vistasDePartes;
        Barco barcoAux;
        for (int i = 0; i < barcos.size(); i++) {
            barcoAux = barcos.get(i);
            vistasDePartes = barcoAux.getVistasFactory().crearVistas();
            for (int j = 0; j < vistasDePartes.size(); j++) {
                this.gameLoop.agregar(vistasDePartes.get(j));
            }
        }
        Fondo fondo = new Fondo(0, 0, 400, 400, "imagenes/mar.png");
        this.gameLoop.agregar(fondo);
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

    private JPanel addSuperficiePanel(int posicionX, int posicionY, int tamX, int tamY) {
        JPanel panel = new SuperficiePanel();
        panel.setOpaque(true);
        panel.setVisible(true);
        panel.addMouseListener(this);
        panel.setBounds(posicionX, posicionY, tamX, tamY);
        frame.getContentPane().add(panel);

        return panel;
    }

    private JButton addBotonDetener(int posicionX, int posicionY, int tamX, int tamY) {
        // I
        // JPanel fondo = new Fondo(0, 0, 400, 400, "imagenes/mar.png");
        // panel.add(comp)
        ImageIcon iconoCerrar = new ImageIcon("imagenes/iconos/cerrar.png");
        JButton btnDetener = new JButton("Detener", iconoCerrar);
        btnDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLoop.detenerEjecucion();
            }
        });
        btnDetener.setBounds(posicionX, posicionY, tamX, tamY);
        frame.getContentPane().add(btnDetener);
        return btnDetener;
    }

    private JButton addBotonPasarTurno(int posicionX, int posicionY, int tamX, int tamY) {
        ImageIcon iconoPasarTurno = new ImageIcon("imagenes/iconos/pasar_turno.png");
        JButton btnPasarTurno = new JButton("Pasar Turno", iconoPasarTurno);
        btnPasarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                partida.pasarTurno();
                puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
                verificarFinDelJuego();
            }
        });
        btnPasarTurno.setBounds(posicionX, posicionY, tamX, tamY);
        frame.getContentPane().add(btnPasarTurno);
        return btnPasarTurno;
    }

    public void verificarFinDelJuego() {
        if (partida.gano()) {
            JFrame frame2 = new JFrame();
            frame2.setForeground(new Color(0, 0, 0));
            frame2.setBounds(100, 100, 800, 1000);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.getContentPane().setLayout(null);
            frame2.setTitle("Batalla Naval - Grupo 11");
            frame.dispose();
            frame2.setVisible(true);
            JLabel gano = new JLabel("Has ganado");
            gano.setBounds(200, 200, 200, 200);
            frame2.add(gano);
        }
        if (partida.perdio()) {
            JFrame frame2 = new JFrame();
            frame2.setForeground(new Color(0, 0, 0));
            frame2.setBounds(100, 100, 800, 1000);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.getContentPane().setLayout(null);
            frame2.setTitle("Batalla Naval - Grupo 11");
            frame.dispose();
            frame2.setVisible(true);
            JLabel perdio = new JLabel("Has perdido..");
            perdio.setBounds(200, 200, 200, 200);
            frame2.add(perdio);
        }
    }

    private JButton addBotonIniciar(int posicionX, int posicionY, int tamX, int tamY) {
        ImageIcon iconoIniciar = new ImageIcon("imagenes/iconos/iniciar.png");
        JButton btnIniciar = new JButton("Iniciar", iconoIniciar);
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameLoop.iniciarEjecucion();
            }
        });
        btnIniciar.setBounds(posicionX, posicionY, tamX, tamY);
        frame.getContentPane().add(btnIniciar);
        return btnIniciar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ultimaPosicionClickeada.setX((e.getX() / 40) + 1);
        ultimaPosicionClickeada.setY((e.getY() / 40) + 1);
        posicionClickeada.setText("Posicion a afectar: (" + Integer.toString(ultimaPosicionClickeada.x()) + "," + Integer.toString(ultimaPosicionClickeada.y()) + ")");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
