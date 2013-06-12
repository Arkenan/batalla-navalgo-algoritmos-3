package casillero;

import java.util.ArrayList;

import partes.Parte;

public class Casillero {
    private final ArrayList<Parte> elementos = new ArrayList<Parte>();

    public void colocarElemento(Parte parte) {
        elementos.add(parte);
    }

    // para qué usamos esto?
    public boolean contienePartes() {
        return !(elementos.isEmpty());
    }

    public ArrayList<Parte> obtenerPartes() {
        return elementos;
    }

    public void quitarElemento(Parte elemento) {
        elementos.remove(elemento);
    }

    public boolean contiene(Parte elemento) {
        return elementos.contains(elemento);
    }
}
