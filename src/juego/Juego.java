package juego;

import java.util.ArrayList;

import barcos.Barco;
import barcos.Vector;
import disparos.Disparo;
import excepciones.DisparoInvalido;

public class Juego {
    private int puntos;
    private final int decrecimientoDePuntosPorPasoDeTurno;
    ArrayList<Disparo> disparos;
    ManejadorDeBarcos manejadorDeBarcos;

    public Juego() {
        puntos = 10000;
        decrecimientoDePuntosPorPasoDeTurno = 10;
        disparos = new ArrayList<Disparo>();
        manejadorDeBarcos = new ManejadorDeBarcos();

    }

    public int getPuntos() {
        return puntos;
    }

    // Paso de turno sin realizar acciones.
    public void pasarTurno() {
        this.reducirPuntosEn(decrecimientoDePuntosPorPasoDeTurno);
        this.realizarCambiosPasoTurno();
    }

    // Nos devuelve un booleano que informa si los puntos son suficientes o no para seguir jugando.
    public boolean faltanPuntosParaSeguir() {
        return (puntos < 10);
    }

    // Este metodo se ocupa del movimiento de barcos y explosion de minas.
    public void realizarCambiosPasoTurno() {
        this.pasoTurnoDisparos();
        manejadorDeBarcos.moverBarcos();
    }

    private void pasoTurnoDisparos() {
        Disparo disparoAuxiliar;
        int i = 0;
        while (i < disparos.size()) {
            if ((disparos.get(i)).debeExplotar()) {
                disparoAuxiliar = disparos.remove(i);
                disparoAuxiliar.explotar();
            } else {

                disparoAuxiliar = disparos.get(i);
                disparoAuxiliar.pasarTurno();
                i++;
            }
        }

    }

    public boolean gano() {
        return manejadorDeBarcos.todosLosBarcosEstanDestruidos();
    }

    public boolean perdio() {
        if (!gano()) {
            return this.faltanPuntosParaSeguir();
        }
        return false;
    }

    public void colocarDisparo(String nombre, Vector posicion) throws DisparoInvalido {
        ConstructorDeDisparo constructorDeDisparo = new ConstructorDeDisparo();
        Disparo unDisparo = constructorDeDisparo.construirDisparo(nombre, posicion);
        disparos.add(unDisparo);
        this.reducirPuntosEn(unDisparo.costo());
        this.realizarCambiosPasoTurno();
    }

    private void reducirPuntosEn(int puntosADisminuir) {
        puntos -= puntosADisminuir;
    }

    public ArrayList<Barco> obtenerBarcos() {
        return manejadorDeBarcos.obtenerBarcos();
    }
}
