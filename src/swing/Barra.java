package swing;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class Barra extends JFrame {

    public Barra() {
        super("Batalla Naval - Grupo 11");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // crear iconos

        ImageIcon iconoIniciar = new ImageIcon("iniciar.png");
        ImageIcon iconoGuardar = new ImageIcon("guardar.png");
        ImageIcon iconoCerrar = new ImageIcon("cerrar.png");
        ImageIcon pasarTurno = new ImageIcon("pasar_turno.png");

        // crear iconos para disparar

        ImageIcon iconoDisparar = new ImageIcon("disparo.png");
        ImageIcon iconoMina = new ImageIcon("mina.png");
        ImageIcon iconoMinaRadio1 = new ImageIcon("mina_radio1.png");
        ImageIcon iconoMinaRadio2 = new ImageIcon("mina_radio2.png");

        JButton iniciar = new JButton("Iniciar", iconoIniciar);
        JButton guardar = new JButton("Guardar", iconoGuardar);
        JButton cerrar = new JButton("Cerrar", iconoCerrar);
        JButton siguienteTurno = new JButton("Pasar Turno", pasarTurno);

        JButton disparar = new JButton("Disparar", iconoDisparar);
        JButton mina = new JButton("Mina", iconoMina);
        JButton mina1 = new JButton("Mina Radio 1", iconoMinaRadio1);
        JButton mina2 = new JButton("Mina Radio 2", iconoMinaRadio2);

        JToolBar barraPrincipal = new JToolBar();
        JToolBar barraParaDaniar = new JToolBar();

        barraPrincipal.add(iniciar);
        barraPrincipal.add(guardar);
        barraPrincipal.add(cerrar);
        barraPrincipal.add(siguienteTurno);

        barraParaDaniar.add(disparar);
        barraParaDaniar.add(mina);
        barraParaDaniar.add(mina1);
        barraParaDaniar.add(mina2);

        BorderLayout borde = new BorderLayout();
        this.setLayout(borde);
        this.add("North", barraPrincipal);
        this.add("South", barraParaDaniar);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] arguments) {
        Barra barra = new Barra();
    }
}
