package escenario;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import partes.Parte;
import barcos.Barco;
import barcos.Vector;
import casillero.Casillero;

public final class Tablero {

    private final Map<String, Casillero> casilleros;
    private final int ancho;
    private final int alto;

    // esto hay que llenarlo con los valores iniciales de los atributos que
    // vayamos a tener.
    private Tablero(int ancho, int alto) {
        casilleros = new HashMap<String, Casillero>();
        this.ancho = ancho;
        this.alto = alto;
    }

    private static Tablero tableroUnico = new Tablero(10, 10);

    public static Tablero getTablero() {
        return tableroUnico;
    }

    private String obtenerClave(int x, int y) {
        String clave = (Integer.toString(x) + Integer.toString(y));
        return clave;
    }

    public Casillero obtenerCasillero(int x, int y) {
        String clave = obtenerClave(x, y);
        if (!casilleros.containsKey(clave)) {
            casilleros.put(clave, new Casillero());
        }
        return casilleros.get(clave);
    }

    public Casillero obtenerCasillero(Vector posicion) {
        return (obtenerCasillero(posicion.x(), posicion.y()));
    }

    public boolean fueraDeRango(Vector posicion) {
        boolean fuera = false;
        if (posicion.x() > ancho || posicion.x() < 1 || posicion.y() < 1 || posicion.y() > alto) {
            fuera = true;
        }
        return fuera;
    }

    public void removerBarco(Barco barco) {
        // de todas sus casillas. FATLA IMPLEMENTAR.
    }

    public void colocarElemento(Vector posicion, Parte elemento) {
        obtenerCasillero(posicion).colocarElemento(elemento);
    }

    public void sacarElemento(Vector posicion, Parte elemento) {
    }

    public LinkedList<Casillero> casillasAfectadas(Vector posicion, int radio) {
        LinkedList<Casillero> lista = new LinkedList<Casillero>();
        for (int columna = posicion.x() - radio; columna <= posicion.x() + radio; columna++) {
            for (int fila = posicion.y() - radio; fila <= posicion.y() + radio; fila++) {
                lista.add(obtenerCasillero(columna, fila));
            }
        }
        return lista;
    }

    public boolean elementoPerteneceAlCasillero(Vector posicion, Parte elemento) {
        return obtenerCasillero(posicion).contiene(elemento);
    }

}