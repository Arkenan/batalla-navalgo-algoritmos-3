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
	JLabel puntaje, armaSeleccionada;
	protected ArrayList<VistaDaniador> vistasDaniadores;
	protected ArrayList<VistaBarco> vistasBarcos;
	protected Dibujante dibujante;
	final static String DISPARO_POR_DEFAULT = "disparoconvencional";

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

	public VentanaJuego(String dirArchivo) {
		try {
			initialize(dirArchivo);
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
		posicionY += tamY;

		JButton btnDisparar = this.addBotonDisparar(posicion, posicionY);
		posicionY += tamY;

		JButton btnMina = this.addBotonMina(posicion, posicionY);
		posicionY += tamY;

		JButton btnMina1 = this.addBotonMina1(posicion, posicionY);
		posicionY += tamY;

		JButton btnMina2 = this.addBotonMina2(posicion, posicionY);

		int posicionPanelX = posicion + tamX;
		int posicionPanelY = 0;
		int tamPanelX = 400;
		int tamPanelY = 400;
		JPanel panel = this.addSuperficiePanel(posicionPanelX, posicionPanelY,
				tamPanelX, tamPanelY);

		int posicionX = posicionPanelX + tamPanelX;
		int posicionY1 = 50;

		this.addAyudaBuque();

		this.gameLoop = new GameLoop((SuperficieDeDibujo) panel);

		this.inicializarModelo(dirArchivo);

		puntaje = this.addPuntaje();

		armaSeleccionada = this.addArmaSeleccionada();

		this.addMouseListener(panel);

		this.addKeyListener();

		this.setComponentsFocus(btnIniciar, btnDetener);

	}

	private JLabel addPuntaje() {
		int puntos = partida.getPuntos();
		JLabel puntaje = new JLabel("Puntaje:" + Integer.toString(puntos));
		puntaje.setBounds(50, 5, 200, 20);
		frame.add(puntaje);
		return puntaje;
	}

	private JLabel addArmaSeleccionada() {
		int puntos = partida.getPuntos();
		JLabel puntaje = new JLabel("Arma seleccionada:" + " "
				+ disparoSeleccionado);
		puntaje.setBounds(0, 25, 250, 20);
		frame.add(puntaje);
		return puntaje;
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
		case "disparoconvencional":
			colocarDisparoConvencional(nombre, posicion);
		}
	}

	private void colocarDisparoConvencional(String nombre,
			Vector posicionClickeada) {
		Vector posicionDaniador = new Vector(posicionClickeada.x(),
				posicionClickeada.y());
		DisparoConvencional unDisparo = (DisparoConvencional) partida
				.crearDaniador(nombre, posicionDaniador);
		VistaDisparoConvencional vistaDisparo = new VistaDisparoConvencional(
				unDisparo);
		dibujante.agregarDisparo(vistaDisparo);
		vistaDisparo.agregarObservador(this);
		vistasDaniadores.add(vistaDisparo);
		partida.colocarDaniador(unDisparo);
		puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
		verificarFinDelJuego();
	}

	private void colocarMina(String nombre, Vector posicionClickeada) {
		Vector posicionDaniador = new Vector(posicionClickeada.x(),
				posicionClickeada.y());
		Mina unaMina = (Mina) partida.crearDaniador(nombre, posicionDaniador);
		VistaMina vistaMina = new VistaMina(unaMina);
		dibujante.agregarMina(vistaMina);
		vistaMina.agregarObservador(this);
		vistasDaniadores.add(vistaMina);
		partida.colocarDaniador(unaMina);
		puntaje.setText("Puntaje:" + Integer.toString(partida.getPuntos()));
		verificarFinDelJuego();
	}

	private void seleccionArma(String nombre, String tipo) {
		disparoSeleccionado = nombre;
		tipoDisparoSeleccionado = tipo;
		armaSeleccionada.setText("Arma seleccionada:" + " "
				+ disparoSeleccionado);
	}

	private JButton addBotonMina2(int posicionX, int posicionY) {
		Boton btnMina2 = new Boton(posicionX, posicionY,
				"imagenes/iconos/mina_radio2.png", "Mina Radio 2");
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
		Boton btnMina = new Boton(posicionX, posicionY,
				"imagenes/iconos/mina_contacto.png", "Mina Contacto");
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
		Boton btnMina1 = new Boton(posicionX, posicionY,
				"imagenes/iconos/mina_radio1.png", "Mina Radio 1");
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
		Boton btnDisparar = new Boton(posicionX, posicionY,
				"imagenes/iconos/disparo.png", "Disparar");
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
		/* PARA NIVELES Y PARTIDAS */
		if (!dirArchivo.equals("")) {
			System.out.println(dirArchivo);
			ArrayList<Daniador> disparos = new ArrayList<Daniador>();
			Element nodoPartida = Partida.obtenerNodoPartida("niveles XML/"
					+ dirArchivo + ".xml");
			partida = new Partida(nodoPartida);
			barcos = partida.crearBarcos(nodoPartida);
		} else {
			partida = new Partida();
			barcos = partida.crearBarcosPorDefault();
		}

		disparoSeleccionado = DISPARO_POR_DEFAULT;
		tipoDisparoSeleccionado = DISPARO_POR_DEFAULT;

		dibujante = new Dibujante(new Fondo(0, 0, 400, 400, "imagenes/mar.png"));

		ArrayList<AbstractVistasBarcoFactory> constructoresDeVistas = partida
				.getConstructoresDeVistasPorDefault();
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

	private JPanel addSuperficiePanel(int posicionX, int posicionY, int tamX,
			int tamY) {
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
		Boton btnDetener = new Boton(posicionX, posicionY,
				"imagenes/iconos/cerrar.png", "Detener");
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
		Boton btnPasarTurno = new Boton(posicionX, posicionY,
				"imagenes/iconos/pasar_turno.png", "Pasar Turno");
		btnPasarTurno.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				partida.pasarTurno();
				puntaje.setText("Puntaje:"
						+ Integer.toString(partida.getPuntos()));
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
		Boton btnIniciar = new Boton(posicionX, posicionY,
				"imagenes/iconos/iniciar.png", "Iniciar");
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
			this.colocarDaniador(disparoSeleccionado, tipoDisparoSeleccionado,
					posicion);
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
				for (int j = 0; j < 20; j++)
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
