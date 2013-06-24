package barcos;

import java.util.ArrayList;

import partes.Parte;
import barcos.strategies.MovimientoStrategy;
import escenario.Tablero;
import excepciones.PosicionInvalida;

/*
 * Direccion Cara 1 horizontal 0 vertical
 */

public abstract class Barco {
    protected int tamanio, vida;
    protected Vector orientacion, posicion;
    protected ArrayList<Parte> partesDelBarco;
    protected MovimientoStrategy estrategia;

    /*
     * los datos vendrian a ser cantidad de partes, la vid y orientación de cara (hacia donde
     * apunta).
     */
    public Barco(Vector orient, int tam, int vida, MovimientoStrategy estrategia) {
        tamanio = tam;
        orientacion = orient;
        partesDelBarco = new ArrayList<Parte>();
        this.vida = vida;
        this.construirPartes();
        this.estrategia = estrategia;
    }

    public void colocarEnTablero(Vector posicion) throws PosicionInvalida {
        if (this.verificarPosicion(posicion) == false) {
            throw new PosicionInvalida("HAY UNA POSICION INVALIDA");
        }
        this.posicion = posicion;
        this.colocarPartes();
    }

    /* verifica parte por parte que no este fuera de rango. */
    public boolean verificarPosicion(Vector pos) {
        Tablero tablero = Tablero.getTablero();
        for (int i = 0; i < tamanio; i++) {
            Vector vector = pos.sumar(orientacion.porEscalar(i));
            if (tablero.fueraDeRango(vector) == true) {
                return false;
            }
        }
        return true;
    }

    /*
     * // METODOS PUBLICOS // para el principio de cada turno. public void moverse() {
     * this.cambiarPosicion(); this.moverPartes(); }
     */
    abstract boolean estaDaniado();

    abstract void construirPartes();

    public int obtenerTamanio() {
        return tamanio;
    }

    public Vector obtenerOrientacion() {
        return orientacion;
    }

    public ArrayList<Parte> obtenerPartes() {
        return partesDelBarco;
    }

    public void metodoPrueba() {

    }

    // METODOS PRIVADOS

    public Vector obtenerDireccionMovimiento() {
        return estrategia.obtenerDireccionMovimiento();
    }

    /* PARA IMPLEMENTAR LUEGO. private string posicionActual(); */

    public boolean estaDestruido() {
        for (int i = 0; i < tamanio; i++) {
            Parte parte = partesDelBarco.get(i);
            if (parte.estaDestruida() == false) {
                return false;
            }
        }
        return true;
    }

    public Vector obtenerPosicion() {
        return posicion;
    }

    public void moverse() {
        Vector nuevaPosicion = estrategia.ejecutar();
        sacarPartes();
        posicion = nuevaPosicion;
        colocarPartes();
    }

    /* quita todas las partes de un barco del tablero */
    private void sacarPartes() {
        Tablero tablero = Tablero.getTablero();
        for (int i = 0; i < tamanio; i++) {
            Vector posParte = new Vector(posicion.sumar(orientacion.porEscalar(i)));
            tablero.sacarElemento(posParte, partesDelBarco.get(i));
        }
    }

    /*
     * Calcula la posicion de cada parte en base a la posicion de la cabeza.
     */
    private void colocarPartes() {
        Tablero tablero = Tablero.getTablero();
        for (int i = 0; i < tamanio; i++) {
            Vector posParte = new Vector(posicion.sumar(orientacion.porEscalar(i)));
            partesDelBarco.get(i).cambiarPosicion(posParte);
            tablero.colocarElemento(posParte, partesDelBarco.get(i));
        }
    }
}