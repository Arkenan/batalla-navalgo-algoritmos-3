package partida;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;

import barcos.Vector;
import disparos.Disparo;
import disparos.DisparoConvencional;
import disparos.MinaContacto;
import disparos.MinaDobleRadio;
import disparos.MinaRadio;
import excepciones.DisparoInvalido;
import excepciones.PosicionInvalida;

public class ConstructorDeDisparo {
    ArrayList<Disparo> disparosPosibles;

    public ConstructorDeDisparo() {
        disparosPosibles = new ArrayList<Disparo>();
        disparosPosibles.add(new MinaContacto());
        disparosPosibles.add(new MinaDobleRadio());
        disparosPosibles.add(new MinaRadio());
        disparosPosibles.add(new DisparoConvencional());
    }

    public Disparo construirDisparo(String nombre, Vector posicion) throws DisparoInvalido {
        String nombrePosible;
        Disparo disparoADevolver;

        for (int i = 0; i < disparosPosibles.size(); i++) {

            nombrePosible = (disparosPosibles.get(i)).obtenerNombre();

            if (nombre.equals(nombrePosible)) {

                disparoADevolver = disparosPosibles.get(i);

                try {
                    disparoADevolver.cambiarCasillerosAfectados(posicion);
                } catch (PosicionInvalida error) {
                    throw new DisparoInvalido();
                }

                return disparoADevolver;
            }

        }
        throw new DisparoInvalido();
    }

    // ESTO NO TOMA EN CUENTA HACE CUANTOS TURNOS FUE PUESTA LA MINA. AGREGAR.
    public ArrayList<Disparo> construirDisparos(Element nodoDisparos) {
        Element nodoDisparo;
        ArrayList<Disparo> listaADevolver = new ArrayList<Disparo>();

        // obtengo los nodos de cada disparo en una lista.
        List<Element> listaNodosDisparo = nodoDisparos.elements();
        Iterator<Element> iterador = listaNodosDisparo.iterator();

        while (iterador.hasNext()) {
            nodoDisparo = iterador.next();
            Vector posicion = new Vector(nodoDisparo.attributeValue("posicion"));
            listaADevolver.add(this.construirDisparo(nodoDisparo.getName().toLowerCase(), posicion));
        }
        return listaADevolver;
    }
}
