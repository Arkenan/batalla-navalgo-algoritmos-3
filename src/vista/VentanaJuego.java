package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import observador.Observador;

import org.dom4j.Element;

import partida.Partida;
import vistadaniadores.VistaDaniador;
import vistadaniadores.VistaDisparoConvencional;
import vistadaniadores.VistaMina;
import vistasbarcos.VistaBarco;
import vistasbarcos.VistaParte;
import barcos.Barco;
import barcos.Vector;
import constructoresdevistas.AbstractVistasBarcoFactory;
import disparos.Daniador;
import disparos.DisparoConvencional;
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
    JLabel puntaje, armaSeleccionada, imagenArma, fondo;
    protected ArrayList<VistaDaniador> vistasDaniadores;
    protected ArrayList<VistaBarco> vistasBarcos;
    protected Dibujante dibujante;
    final static String DISPARO_POR_DEFAULT = "disparoconvencional";
    ManejadorVentanas manejador;

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
            initialize("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public VentanaJuego(String dirArchivo, ManejadorVentanas manejadorV) {
        try {
            initialize(dirArchivo);
            manejador = manejadorV;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public VentanaJuego(ManejadorVentanas manejadorV) {
        try {
            initialize("");
            manejador = manejadorV;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize the contents of the frame.
     * 
     * @throws IOException
     */
    private void initialize(String dirArchivo) throws IOException {
        frame = new JFrame();
        frame.setForeground(new Color(0, 0, 0));
        frame.setSize(800, 481);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setTitle("Batalla Naval - Grupo 11");

        vistasBarcos = new ArrayList<VistaBarco>();

        vistasDaniadores = new ArrayList<VistaDaniador>();

        int tamX = 250;

        int tamY = 50;
        int tamY2 = 70;

        int posicion = 50;

        int posicionY = 50;

        this.addBotonIniciar(posicion, posicionY);

        posicionY += tamY2;

        this.addBotonPasarTurno(posicion, posicionY);
        posicionY += tamY2;

        this.addBotonDisparar(posicion, posicionY);
        posicionY += tamY2;

        this.addBotonMina(posicion, posicionY);
        posicionY += tamY2;

        this.addBotonMina1(posicion, posicionY);
        posicionY += tamY2;

        this.addBotonMina2(posicion, posicionY);

        this.addBtnGuardar();

        int posicionPanelX = posicion + tamX + 50;
        int posicionPanelY = 50;
        int tamPanelX = 400;
        int tamPanelY = 400;
        JPanel panel = this.addSuperficiePanel(posicionPanelX, posicionPanelY, tamPanelX, tamPanelY);

        this.addBotonVolver(550, 5);

        this.gameLoop = new GameLoop((SuperficieDeDibujo) panel);

        this.inicializarModelo(dirArchivo);

        puntaje = this.addPuntaje();

        armaSeleccionada = this.addArmaSeleccionada();
        imagenArma = this.addImagenArma();
        fondo = this.addFondo();

        this.addMouseListener(panel);

        this.addKeyListener();

        // this.setComponentsFocus(btnIniciar, btnDetener);

    }

    private JLabel addPuntaje() {
        int puntos = partida.getPuntos();
        JLabel puntaje = new JLabel("Puntaje:" + Integer.toString(puntos));
        Font font = new Font("Vladimir Script", Font.ITALIC, 30);
        puntaje.setFont(font);
        puntaje.setBounds(50, 5, 200, 45);
        frame.add(puntaje);
        return puntaje;
    }

    private void addBtnGuardar() {
        JButton btnGuardar = new JButton("guardar");
        btnGuardar.setBounds(650, 5, 100, 40);
        btnGuardar.setVisible(true);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos XML", "xml");
                fileChooser.setFileFilter(filtro);
                fileChooser.setCurrentDirectory(new File("Niveles XML"));
                int seleccion = fileChooser.showSaveDialog(null);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File fichero = fileChooser.getSelectedFile();
                    String[] partesDelNombre = fichero.getName().split("\\.");
                    if (partesDelNombre.length == 1) {
                        try {
                            partida.guardar(fichero.getAbsolutePath() + ".xml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (partesDelNombre.length == 2) {
                            if (partesDelNombre[1].equals("xml")) {
                                try {
                                    partida.guardar(fichero.getAbsolutePath());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                this.actionPerformed(arg0);
                            }
                        } else {
                            this.actionPerformed(arg0);
                        }
                    }
                }
            }
        });
        frame.getContentPane().add(btnGuardar);
    }

    private JLabel addFondo() {
        ImageIcon imagenFondo = new ImageIcon("imagenes/inicio/fondojuego.jpg");
        JLabel fondo = new JLabel(imagenFondo);
        fondo.setBounds(0, 0, 800, 481);
        frame.add(fondo);
        return fondo;
    }

    private JLabel addArmaSeleccionada() {
        JLabel armaSeleccionada = new JLabel("Arma seleccionada:");
        armaSeleccionada.setBounds(225, 5, 150, 45);
        frame.add(armaSeleccionada);
        return armaSeleccionada;
    }

    private ImageIcon obtenerImagenArma() {
        String direccion = "imagenes/iconos/disparo.png";
        switch (disparoSeleccionado) {
            case "minacontacto":
                direccion = "imagenes/iconos/mina_contacto.png";
                break;
            case "minaradio":
                direccion = "imagenes/iconos/mina_radio1.png";
                break;
            case "minadobleradio":
                direccion = "imagenes/iconos/mina_radio2.png";
                break;
            case "disparoconvencional":
                direccion = "imagenes/iconos/disparo.png";
                break;
        }
        ImageIcon imagenArma = new ImageIcon(direccion);
        return imagenArma;
    }

    private JLabel addImagenArma() {
        JLabel imagenArma = new JLabel(this.obtenerImagenArma());
        imagenArma.setBounds(300, 5, 150, 45);
        frame.add(imagenArma);
        return imagenArma;
    }

    public void setVisible(boolean valor) {
        frame.setVisible(valor);
    }

    private void colocarDaniador(String nombre, String tipo, Vector posicion) {
        switch (tipo) {
            case "mina":
                colocarMina(nombre, posicion);
                break;
            case "disparoconvencional":
                colocarDisparoConvencional(nombre, posicion);
        }
    }

    private void colocarDisparoConvencional(String nombre, Vector posicionClickeada) {
        Vector posicionDaniador = new Vector(posicionClickeada.x(), posicionClickeada.y());
        DisparoConvencional unDisparo = (DisparoConvencional) partida.crearDaniador(nombre, posicionDaniador);
        VistaDisparoConvencional vistaDisparo = new VistaDisparoConvencional(unDisparo);
        if (unDisparo.costo() < partida.getPuntos()) {
            dibujante.agregarDisparo(vistaDisparo);
            vistaDisparo.agregarObservador(this);
            vistasDaniadores.add(vistaDisparo);
            partida.colocarDaniador(unDisparo);
            puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
            verificarFinDelJuego();
        }
    }

    private void colocarMina(String nombre, Vector posicionClickeada) {
        Vector posicionDaniador = new Vector(posicionClickeada.x(), posicionClickeada.y());
        Mina unaMina = (Mina) partida.crearDaniador(nombre, posicionDaniador);
        VistaMina vistaMina = new VistaMina(unaMina);
        if (unaMina.costo() < partida.getPuntos()) {
            dibujante.agregarMina(vistaMina);
            vistaMina.agregarObservador(this);
            vistasDaniadores.add(vistaMina);
            partida.colocarDaniador(unaMina);
            puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
            verificarFinDelJuego();
        }
    }

    private void seleccionArma(String nombre, String tipo) {
        disparoSeleccionado = nombre;
        tipoDisparoSeleccionado = tipo;
        imagenArma.setIcon(this.obtenerImagenArma());

    }

    private JLabel actualizarImagenArma() {
        frame.remove(imagenArma);
        return this.addArmaSeleccionada();
    }

    private JButton addBotonMina2(int posicionX, int posicionY) {
        BotonJuego btnMina2 = new BotonJuego(posicionX, posicionY, "imagenes/iconos/mina_radio2.png", "Mina Radio 2 | Costo: 100");
        btnMina2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                seleccionArma("minadobleradio", "mina");
            }
        });
        frame.getContentPane().add(btnMina2);
        return btnMina2;
    }

    private JButton addBotonMina(int posicionX, int posicionY) {
        BotonJuego btnMina = new BotonJuego(posicionX, posicionY, "imagenes/iconos/mina_contacto.png", "Mina Contacto | Costo: 150");
        btnMina.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                seleccionArma("minacontacto", "mina");
            }
        });
        frame.getContentPane().add(btnMina);
        return btnMina;
    }

    private JButton addBotonMina1(int posicionX, int posicionY) {
        BotonJuego btnMina1 = new BotonJuego(posicionX, posicionY, "imagenes/iconos/mina_radio1.png", "Mina Radio 1 | Costo: 50");
        btnMina1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                seleccionArma("minaradio", "mina");
            }
        });
        frame.getContentPane().add(btnMina1);
        return btnMina1;
    }

    private JButton addBotonDisparar(int posicionX, int posicionY) {
        BotonJuego btnDisparar = new BotonJuego(posicionX, posicionY, "imagenes/iconos/disparo.png", "Disparar | Costo: 200");
        btnDisparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                seleccionArma("disparoconvencional", "disparoconvencional");
            }
        });
        frame.getContentPane().add(btnDisparar);
        return btnDisparar;
    }

    private void inicializarModelo(String dirArchivo) throws IOException {
        ArrayList<Barco> barcos;
        ArrayList<AbstractVistasBarcoFactory> constructoresDeVistas;
        /* PARA NIVELES Y PARTIDAS */
        if (!dirArchivo.equals("")) {
            // fijarse de los disparos para juegos guardados!
            ArrayList<Daniador> disparos = new ArrayList<Daniador>();
            Element nodoPartida = Partida.obtenerNodoPartida(dirArchivo);

            partida = new Partida(nodoPartida);
            barcos = partida.crearBarcos(nodoPartida);
            constructoresDeVistas = VistaBarco.obtenerVistas(nodoPartida);
        } else {
            partida = new Partida();
            barcos = partida.crearBarcosPorDefault();
            constructoresDeVistas = partida.getConstructoresDeVistasPorDefault();
        }

        disparoSeleccionado = DISPARO_POR_DEFAULT;
        tipoDisparoSeleccionado = DISPARO_POR_DEFAULT;

        dibujante = new Dibujante(new Fondo(0, 0, 400, 400, "imagenes/mar.png"));

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
            dibujante.agregarVistaBarco(vistaBarcoAuxiliar);
        }

        gameLoop.agregar(dibujante);
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
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
            }

            @Override
            public void keyPressed(KeyEvent arg0) {
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

    private JButton addBotonVolver(int posicionX, int posicionY) {
        JButton btnVolver = new JButton("Volver al menu");
        btnVolver.setBounds(525, 5, 120, 40);
        btnVolver.setVisible(true);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameLoop.estaEjecutando() == true) {
                    gameLoop.detenerEjecucion();
                }
                manejador.abrirInicio(frame);
            }
        });
        frame.getContentPane().add(btnVolver);
        return btnVolver;
    }

    private JButton addBotonPasarTurno(int posicionX, int posicionY) {
        BotonJuego btnPasarTurno = new BotonJuego(posicionX, posicionY, "imagenes/iconos/pasar_turno.png", "Pasar Turno | Costo: 10");
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
            VentanaFondo ventana = new VentanaFondo(manejador, "VICTORIA", "imagenes/victoria");
            ventana.setVisible(true);
            frame.dispose();

        }
        if (partida.perdio()) {
            VentanaFondo ventana = new VentanaFondo(manejador, "DERROTA", "imagenes/derrota");
            ventana.setVisible(true);
            frame.dispose();
        }
    }

    private JButton addBotonIniciar(int posicionX, int posicionY) {
        BotonJuego btnIniciar = new BotonJuego(posicionX, posicionY, "imagenes/iconos/iniciar.png", "Iniciar");
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
            if (vistaDaniadorAux.obtenerEstado().equals("gastado")) {
                dibujante.cambiarAlTope(vistaDaniadorAux);
                dibujante.dibujar(gameLoop.getSuperficieDeDibujo());
                dibujante.remover(vistaDaniadorAux);
                vistasDaniadores.remove(vistaDaniadorAux);
            }
        }
        for (int j = 0; j < vistasBarcos.size(); j++) {
            vistaBarcoAux = vistasBarcos.get(j);
            if (vistaBarcoAux.estaDestruido()) {
                dibujante.remover(vistaBarcoAux);
                vistasBarcos.remove(vistaBarcoAux);
            }
        }
    }
}