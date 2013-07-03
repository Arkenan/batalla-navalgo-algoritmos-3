package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import observador.Observador;
import partida.Partida;
import vistadaniadores.VistaDaniador;
import vistadaniadores.VistaDisparo;
import vistadaniadores.VistaMina;
import vistasbarcos.VistaBarco;
import vistasbarcos.VistaParte;
import barcos.Barco;
import barcos.Vector;
import constructoresdevistas.AbstractVistasBarcoFactory;
import disparos.Disparo;
import disparos.Mina;
import escenario.Tablero;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.GameLoop;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class VentanaJuego extends Ventana implements Observador {

    private JFrame frame;
    private GameLoop gameLoop;
    private Partida partida;
    private String disparoSeleccionado;
    private String tipoDisparoSeleccionado;
    JLabel puntaje;
    protected ArrayList<VistaDaniador> vistasDaniadores;
    protected ArrayList<VistaBarco> vistasBarcos;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    VentanaJuego window = new VentanaJuego();
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
    public VentanaJuego() {
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

        vistasBarcos = new ArrayList<VistaBarco>();

        vistasDaniadores = new ArrayList<VistaDaniador>();

        int tamX = 250;

        int tamY = 50;

        int posicion = 0;

        int posicionY = 50;

        JButton btnIniciar = this.addBotonIniciar(posicion, posicionY);

        posicionY += tamY;

        JButton btnDetener = this.addBotonDetener(posicion, posicionY);

        posicionY += tamY;

        JButton btnPasarTurno = this.addBotonPasarTurno(posicion, posicionY);

        int posicionPanelX = posicion + tamX;

        int posicionPanelY = 50;

        int tamPanelX = 400;

        int tamPanelY = 400;

        JPanel panel = this.addSuperficiePanel(posicionPanelX, posicionPanelY, tamPanelX, tamPanelY);

        int posicionX = posicionPanelX + tamPanelX;

        int posicionY1 = 50;

        JButton btnDisparar = this.addBotonDisparar(posicionX, posicionY1);

        posicionY1 += tamY;

        JButton btnMina = this.addBotonMina(posicionX, posicionY1);

        posicionY1 += tamY;
        JButton btnMina1 = this.addBotonMina1(posicionX, posicionY1);

        posicionY1 += tamY;
        JButton btnMina2 = this.addBotonMina2(posicionX, posicionY1);

        this.addAyudaBuque();

        this.gameLoop = new GameLoop((SuperficieDeDibujo) panel);

        this.inicializarModelo();

        puntaje = this.addCosaPrueba();

        // posicionClickeada = this.addCosaPrueba2();

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

    public void setVisible(boolean valor) {
        frame.setVisible(valor);
    }

    private void addAyudaBuque() {
        ImageIcon icono = new ImageIcon("imagenes/lancha/lancha.png");
        JLabel iconoBuque = new JLabel(icono);
        iconoBuque.setBounds(0, 500, 500, 200);
        frame.getContentPane().add(iconoBuque);

    }

    private void colocarDaniador(String nombre, String tipo, Vector posicion) {
        switch (tipo) {
            case "mina":
                colocarMina(nombre, posicion);
                break;
            case "disparo":
                colocarDisparo(nombre, posicion);
        }
    }

    private void colocarDisparo(String nombre, Vector posicionClickeada) {
        Vector posicionDaniador = new Vector(posicionClickeada.x(), posicionClickeada.y());
        Disparo unDisparo = (Disparo) partida.colocarDaniador(nombre, posicionDaniador);
        VistaDisparo vistaDisparo = new VistaDisparo(unDisparo);
        gameLoop.agregar(vistaDisparo);
        unDisparo.agregarObservador(vistaDisparo);
        vistaDisparo.agregarObservador(this);
        vistasDaniadores.add(vistaDisparo);
        puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
        verificarFinDelJuego();
    }

    private void colocarMina(String nombre, Vector posicionClickeada) {
        Vector posicionDaniador = new Vector(posicionClickeada.x(), posicionClickeada.y());
        Mina unaMina = (Mina) partida.colocarDaniador(nombre, posicionDaniador);
        VistaMina vistaMina = new VistaMina(unaMina);
        unaMina.agregarObservador(vistaMina);
        gameLoop.agregar(vistaMina);
        vistaMina.agregarObservador(this);
        vistasDaniadores.add(vistaMina);
        puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
        verificarFinDelJuego();
    }

    private JButton addBotonMina2(int posicionX, int posicionY) {
        Boton btnMina2 = new Boton(posicionX, posicionY, "imagenes/iconos/mina_radio2.png", "Mina Radio 2");
        btnMina2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                disparoSeleccionado = "minadobleradio";
                tipoDisparoSeleccionado = "mina";
            }
        });
        frame.getContentPane().add(btnMina2);
        return btnMina2;
    }

    private JButton addBotonMina1(int posicionX, int posicionY) {
        Boton btnMina1 = new Boton(posicionX, posicionY, "imagenes/iconos/mina_radio1.png", "Mina Radio 1");
        btnMina1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                disparoSeleccionado = "minaradio";
                tipoDisparoSeleccionado = "mina";
            }
        });
        frame.getContentPane().add(btnMina1);
        return btnMina1;
    }

    private JButton addBotonMina(int posicionX, int posicionY) {
        Boton btnMina = new Boton(posicionX, posicionY, "imagenes/iconos/mina_contacto.png", "Mina Contacto");
        btnMina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                disparoSeleccionado = "minadobleradio";
                tipoDisparoSeleccionado = "mina";
            }
        });
        frame.getContentPane().add(btnMina);
        return btnMina;
    }

    private JButton addBotonDisparar(int posicionX, int posicionY) {
        Boton btnDisparar = new Boton(posicionX, posicionY, "imagenes/iconos/disparo.png", "Disparar");
        btnDisparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                disparoSeleccionado = "disparoconvencional";
                tipoDisparoSeleccionado = "disparo";
            }
        });
        frame.getContentPane().add(btnDisparar);
        return btnDisparar;
    }

    private void inicializarModelo() throws IOException {
        /*
         * PARA NIVELES ArrayList<Disparo> disparos = new ArrayList<Disparo>(); Element nodoPartida
         * = Partida.obtenerNodoPartida("niveles XML/pruebas.xml"); partida = new
         * Partida(nodoPartida); ArrayList<Barco> barcos = partida.crearBarcos(nodoPartida);
         */

        disparoSeleccionado = "disparoconvencional";
        tipoDisparoSeleccionado = "disparo";
        partida = new Partida();

        ArrayList<Barco> barcos = partida.crearBarcosPorDefault();
        ArrayList<AbstractVistasBarcoFactory> constructoresDeVistas = partida.getConstructoresDeVistasPorDefault();
        ArrayList<VistaParte> vistasDePartes;
        Barco barcoAux;
        AbstractVistasBarcoFactory constructorAuxiliar;
        VistaBarco vistaBarcoAuxiliar;
        for (int i = 0; i < barcos.size(); i++) {
            barcoAux = barcos.get(i);
            constructorAuxiliar = constructoresDeVistas.get(i);
            constructorAuxiliar.setBarco(barcoAux);
            vistaBarcoAuxiliar = constructorAuxiliar.crearVista();
            vistaBarcoAuxiliar.agregarObservador(this);
            vistasBarcos.add(vistaBarcoAuxiliar);
            vistasDePartes = vistaBarcoAuxiliar.obtenerVistasPartes();
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
        panel.setBackground(Color.BLUE);
        frame.getContentPane().add(panel);

        return panel;
    }

    private JButton addBotonDetener(int posicionX, int posicionY) {
        Boton btnDetener = new Boton(posicionX, posicionY, "imagenes/iconos/cerrar.png", "Detener");
        btnDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLoop.detenerEjecucion();
            }
        });
        frame.getContentPane().add(btnDetener);
        return btnDetener;
    }

    private JButton addBotonPasarTurno(int posicionX, int posicionY) {
        Boton btnPasarTurno = new Boton(posicionX, posicionY, "imagenes/iconos/pasar_turno.png", "Pasar Turno");
        btnPasarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                partida.pasarTurno();
                puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
                verificarFinDelJuego();
            }
        });
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

    private JButton addBotonIniciar(int posicionX, int posicionY) {
        Boton btnIniciar = new Boton(posicionX, posicionY, "imagenes/iconos/iniciar.png", "Iniciar");
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameLoop.iniciarEjecucion();
            }
        });
        frame.getContentPane().add(btnIniciar);
        return btnIniciar;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Vector posicion = new Vector((e.getX() / 40) + 1, (e.getY() / 40) + 1);
        if (!Tablero.getTablero().fueraDeRango(posicion))
            this.colocarDaniador(disparoSeleccionado, tipoDisparoSeleccionado, posicion);
    }

    @Override
    public void actualizar() {
        VistaBarco vistaBarcoAux;
        VistaDaniador vistaDaniadorAux;
        ArrayList<VistaParte> vistasPartesAux;
        for (int i = 0; i < vistasDaniadores.size(); i++) {
            vistaDaniadorAux = vistasDaniadores.get(i);
            System.out.println(vistaDaniadorAux.obtenerEstado());
            if (vistaDaniadorAux.obtenerEstado().equals("gastado")) {
                vistaDaniadorAux.dibujar(gameLoop.getSuperficieDeDibujo());
                gameLoop.remover(vistaDaniadorAux);
                vistasDaniadores.remove(vistaDaniadorAux);
            }

        }
        for (int j = 0; j < vistasBarcos.size(); j++) {
            vistaBarcoAux = vistasBarcos.get(j);
            if (vistaBarcoAux.estaDestruido()) {
                vistasPartesAux = vistaBarcoAux.obtenerVistasPartes();
                for (int h = 0; h < vistasPartesAux.size(); h++) {
                    gameLoop.remover((vistasPartesAux.get(h)));
                }
                vistasBarcos.remove(vistaBarcoAux);
            }
        }
    }
}
