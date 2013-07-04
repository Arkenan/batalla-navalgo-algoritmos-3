package vista;

import javax.swing.JFrame;

public class ManejadorVentanas {
    VentanaJuego ventanaJuego;
    JFrame ventanaInicio, ventanaAyudaIntro, ventanaAyudaBarcos, ventanaAyudaDisparos;

    public ManejadorVentanas() {
        ventanaInicio = new VentanaInicio(this);
        ventanaAyudaIntro = new VentanaAyudaIntro(this);
        ventanaAyudaDisparos = new VentanaAyudaDisparos(this);
        ventanaJuego = new VentanaJuego();
        ventanaAyudaBarcos = new VentanaAyudaBarcos(this);
        ventanaInicio.setVisible(true);
    }

    public static void main(String[] args) {
        ManejadorVentanas manejador = new ManejadorVentanas();
    }

    public void abrirTutorial(JFrame ventana) {
        ventana.setVisible(false);
        ventanaAyudaIntro.setVisible(true);
    }

    public void abrirJuego(String nombrePartida) {
        ventanaJuego = new VentanaJuego(nombrePartida, this);
        ventanaJuego.setVisible(true);
        ventanaInicio.setVisible(false);
    }

    public void abrirAyudaBarcos(JFrame ventana) {
        ventanaAyudaBarcos.setVisible(true);
        ventana.setVisible(false);
    }

    public void abrirAyudaDisparos(JFrame ventana) {
        ventanaAyudaDisparos.setVisible(true);
        ventana.setVisible(false);
    }

    public void abrirInicio(JFrame ventana) {
        ventanaInicio.setVisible(true);
        if (ventana == null) {
            System.out.println("ppppppppppp");
        }
        ventana.setVisible(false);

    }
}
