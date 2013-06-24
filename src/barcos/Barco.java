package barcos;

import java.util.ArrayList;

import partes.Parte;
import barcos.strategies.MovimientoStrategy;
import escenario.Tablero;
import excepciones.PosicionInvalida;

/*
 * Direccion de Movimiento Movimiento hori super(mov, pos, orient, 4);zontal -1 izquierda 1 derecha
 * Movimiento vertical -1 abajo 1 arriba
 * 
 * Direccion Cara 1 horizontal 0 vertical
 */

public abstract class Barco {
    protected int tamanio, vida;
    protected Vector movimiento, orientacion, posicion;
    protected ArrayList<Parte> partesDelBarco;
    protected MovimientoStrategy estrategia;

    /*
     * los datos vendrian a ser cantidad de partes, la vida, direccion de movimiento y de cara
     * (hacia donde apunta).
     */
    public Barco(Vector mov, Vector orient, int tam, int vida, MovimientoStrategy estrategia) {
        tamanio = tam;
        orientacion = orient;
        movimiento = mov;
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

    // METODOS PRIVADOS
    // para cuando choca con la pared.
    public void invertirDireccionMovimiento() {
        movimiento = movimiento.sumar(movimiento.porEscalar(-2));
    }

    public Vector obtenerDireccionMovimiento() {
        return movimiento;
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
        if (verificarPosicion(nuevaPosicion) == true) {
            sacarPartes();
            posicion = nuevaPosicion;
            colocarPartes();
        } else {
            // TODO tenemos que charlar de esto!
            invertirDireccionMovimiento();
            moverse();
        }
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