package vista;

import javax.swing.JFrame;

public class ManejadorVentanas {
    VentanaJuego ventanaJuego;
    JFrame ventanaInicio, ventanaAyudaIntro, ventanaAyudaBarcos, ventanaAyudaDisparos, ventanaVictoria, ventanaDerrota;

    public ManejadorVentanas() {
        ventanaInicio = new VentanaInicio(this);
        ventanaAyudaIntro = new VentanaAyudaIntro(this);
        ventanaAyudaDisparos = new VentanaAyudaDisparos(this);
        ventanaJuego = new VentanaJuego();
        ventanaAyudaBarcos = new VentanaAyudaBarcos(this);
        ventanaInicio.setVisible(true);
        ventanaVictoria = new VentanaFondo(this, "VICTORIA", "imagenes/victoria");
        ventanaDerrota = new VentanaFondo(this, "DERROTA", "imagenes/derrota");
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
        ventana.setVisible(false);

    }

    public void abrirVentanaVictoria(JFrame ventana) {
        ventanaVictoria.setVisible(true);
        ventana.setVisible(false);
    }

    public void abrirVentanaDerrota(JFrame ventana) {
        ventanaDerrota.setVisible(true);
        ventana.setVisible(false);
    }
}
