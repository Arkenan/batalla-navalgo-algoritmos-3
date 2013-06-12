package escenario;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import partes.Parte;
import barcos.Barco;
import barcos.Vector;
import casillero.Casillero;

// patr�n de dise�o singleton.
public final class Tablero {

    private final Map<String, Casillero> casilleros;
    private final int ancho;
    private final int alto;

    // constructor privado, para que no puedan hacerse m�s.
    // esto hay que llenarlo con los valores iniciales de los atributos que
    // vayamos a tener.
    private Tablero(int ancho, int alto) {
        casilleros = new HashMap<String, Casillero>();
        this.ancho = ancho;
        this.alto = alto;
    }

    // se crea la instancia al inicio de la ejecuci�n general.
    // podemos ver de pasarle en alg�n lado las dimensiones?
    private static Tablero tableroUnico = new Tablero(10, 10);

    // m�todo para acceder globalmente a la instancia �nica.
    public static Tablero getTablero() {
        return tableroUnico;
    }

    // por si hay futuros cambios en la implementaci�n del almacenamiento de casilleros.
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
        // tiene que sacarlo de la lista y adem�s borrar las referencias
        // de todas sus casillas. FATLA IMPLEMENTAR.
    }

    public void colocarElemento(Vector posicion, Parte elemento) {
        obtenerCasillero(posicion).colocarElemento(elemento);
    }

    public void sacarElemento(Vector posicion, Parte elemento) {
        obtenerCasillero(posicion).quitarElemento(elemento);
    }

    // devuelve los casilleros afectados por una explosi�n.
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

    // m�todos para armar. CAMBIAR. 0 horizontal. 1 vertical. OBSOLETO.
    /*
     * public void ponerBarco(Barco barco, int[] posCabeza) throws FueraDeRango {
     * 
     * int[] posCola = new int[2]; int direccion = barco.obtenerOrientacion; posCola[direccion] =
     * posCabeza[direccion] - barco.obtenerTamanio() + 1; posCola[1 - direccion] = posCabeza[1 -
     * direccion]; if (fueraDeRango(posCabeza) || fueraDeRango(posCola)) { throw new FueraDeRango();
     * } barco.guardarPosicion(posCabeza[0], posCabeza[1]); // se pone la parte en cada posicion.
     * ArrayList<Parte> lista = barco.obtenerPartes(); Iterator<Parte> iterador = lista.iterator();
     * int[] cursor = new int[2]; cursor = posCola.clone(); for (int i = 0; i <
     * barco.obtenerTamanio(); i++) { // hay que crear. casilleros.put(obtenerClave(cursor[0],
     * cursor[1]), new Casillero()); Casillero casilleroAux; casilleroAux =
     * obtenerCasillero(cursor[0], cursor[1]); if (lista.isEmpty()) {
     * System.out.println("esta vacia"); } casilleroAux.colocarParte(iterador.next(),
     * barco.obtenerID()); cursor[direccion]++;
     * 
     * }
     * 
     * }
     */
    // lo necesitaremos? Para "destruir" totalmente un barco.

}