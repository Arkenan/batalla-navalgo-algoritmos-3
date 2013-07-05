package partida;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;

import barcos.Vector;
import disparos.Daniador;
import disparos.Disparo;
import disparos.DisparoConvencional;
import disparos.MinaContacto;
import disparos.MinaDobleRadio;
import disparos.MinaRadio;
import excepciones.DisparoInvalido;
import excepciones.PosicionInvalida;

public class ConstructorDeDaniador {
    ArrayList<Disparo> disparosPosibles;

    public ConstructorDeDaniador() {
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

    public ArrayList<Daniador> construirDisparos(Element nodoDisparos) {
        Element nodoDisparo;
        ArrayList<Daniador> listaADevolver = new ArrayList<Daniador>();

        // obtengo los nodos de cada disparo en una lista.
        List<Element> listaNodosDisparo = nodoDisparos.elements();
        Iterator<Element> iterador = listaNodosDisparo.iterator();

        while (iterador.hasNext()) {
            nodoDisparo = iterador.next();
            Vector posicion = new Vector(nodoDisparo.attributeValue("posicion"));
            switch (nodoDisparo.getName()) {
                case "DisparoConvencional":
                    listaADevolver.add(new DisparoConvencional(nodoDisparo));
                    break;
                case "MinaContacto":
                    listaADevolver.add(new MinaContacto(nodoDisparo));
                    break;
                case "MinaRadio":
                    listaADevolver.add(new MinaRadio(nodoDisparo));
                    break;
                case "MinaDobleRadio":
                    listaADevolver.add(new MinaContacto(nodoDisparo));
                    break;
                default:
                    throw (new Error("Disparo no reconocido: " + nodoDisparo.getName()));
            }
        }
        return listaADevolver;
    }
}
