package barcos;

import java.util.ArrayList;

import partes.Parte;
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

    /*
     * los datos vendrian a ser cantidad de partes, la vida, direccion de movimiento y de cara
     * (hacia donde apunta).
     */
    public Barco(Vector mov, Vector pos, Vector orient, int tam, int cantVida) throws PosicionInvalida {
        tamanio = tam;
        orientacion = orient;
        if (this.verificarPosicion(pos) == false) {
            throw new PosicionInvalida("HAY UNA POSICION INVALIDA");
        }
        partesDelBarco = new ArrayList<Parte>();
        movimiento = mov;
        posicion = pos;
        vida = cantVida;
        this.construirPartes();
        this.colocarPartes();
    }

    /* verifica parte por parte que no est� fuera de rango. */
    private boolean verificarPosicion(Vector pos) {
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

    /* Luego de moverse cambia su posicion */
    public void cambiarPosicion() {
        posicion.asignar(posicion.sumar(movimiento));
    }

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
        if (this.verificarSiguienteMovimiento() == true) {
            this.moverPartes();
        } else {
            this.invertirDireccionMovimiento();
            this.moverse();
        }
    }

    public void moverPartes() {
        this.sacarPartes();
        this.cambiarPosicion();
        this.colocarPartes();
    }

    /* quita todas las partes de un barco del tablero */
    public void sacarPartes() {
        Tablero tablero = Tablero.getTablero();
        for (int i = 0; i < tamanio; i++) {
            Vector posParte = new Vector(posicion.sumar(orientacion.porEscalar(i)));
            tablero.sacarElemento(posParte, partesDelBarco.get(i));
        }
    }

    private boolean verificarSiguienteMovimiento() {
        Vector nuevaPosicion = posicion.sumar(movimiento);
        return this.verificarPosicion(nuevaPosicion);
    }

    /*
     * Calcula la posicion de cada parte en base a la posicion de la cabeza.
     */
    public void colocarPartes() {
        Tablero tablero = Tablero.getTablero();
        for (int i = 0; i < tamanio; i++) {
            Vector posParte = new Vector(posicion.sumar(orientacion.porEscalar(i)));
            partesDelBarco.get(i).cambiarPosicion(posParte);
            tablero.colocarElemento(posParte, partesDelBarco.get(i));
        }
    }
}
