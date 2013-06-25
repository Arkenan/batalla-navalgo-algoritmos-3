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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import partes.Parte;
import partida.Partida;
import casillero.Casillero;
import escenario.Tablero;
import fiuba.algo3.titiritero.dibujables.SuperficiePanel;
import fiuba.algo3.titiritero.modelo.GameLoop;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class VentanaPrincipal {

	private JFrame frame;
	private GameLoop gameLoop;
	private Partida partida;

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
			// TODO Auto-generated catch block
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Proyecto Base para uso del Titiritero");

		JButton btnIniciar = this.addBotonIniciar();

		JButton btnDetener = this.addBotonDetener();

		JButton btnPasarTurno = this.addBotonPasarTurno();

		JPanel panel = this.addSuperficiePanel();

		this.gameLoop = new GameLoop((SuperficieDeDibujo) panel);

		this.inicializarModelo();

		this.addMouseListener(panel);

		this.addKeyListener();

		this.setComponentsFocus(btnIniciar, btnDetener);

	}

	private void inicializarModelo() {
		partida = new Partida();
		Tablero tablero = Tablero.getTablero();
		Casillero casilleroAuxiliar;
		ArrayList<Parte> partes;
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; i <= 10; i++) {
				casilleroAuxiliar = tablero.obtenerCasillero(i, j);
				partes = casilleroAuxiliar.obtenerPartes();
				for (int k = 0; k < partes.size(); k++) {
					this.gameLoop.agregar(new VistaPrueba(20, partes.get(k)));
				}
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
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(42, 53, 375, 187);
		frame.getContentPane().add(panel);
		return panel;
	}

	private JButton addBotonDetener() {
		JButton btnDetener = new JButton("Detener");
		btnDetener.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameLoop.detenerEjecucion();
			}
		});
		btnDetener.setBounds(325, 16, 92, 25);
		frame.getContentPane().add(btnDetener);
		return btnDetener;
	}

	private JButton addBotonPasarTurno() {
		JButton btnIniciar = new JButton("PasarTurno");
		btnIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				partida.pasarTurno();
			}
		});
		btnIniciar.setBounds(200, 16, 77, 25);
		frame.getContentPane().add(btnIniciar);
		return btnIniciar;
	}

	private JButton addBotonIniciar() {
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameLoop.iniciarEjecucion();
			}
		});
		btnIniciar.setBounds(42, 16, 77, 25);
		frame.getContentPane().add(btnIniciar);
		return btnIniciar;
	}
}
