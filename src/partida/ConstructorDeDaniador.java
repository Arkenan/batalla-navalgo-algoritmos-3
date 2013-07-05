package partida;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;

import vista.Dibujante;
import vistadaniadores.VistaDaniador;
import vistadaniadores.VistaDisparoConvencional;
import vistadaniadores.VistaMina;
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

    public ArrayList<Daniador> construirDaniadores(Element nodoDaniadores) {
        Element nodoDisparo;
        ArrayList<Daniador> listaADevolver = new ArrayList<Daniador>();

        // obtengo los nodos de cada disparo en una lista.
        List<Element> listaNodosDisparo = nodoDaniadores.elements();
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
                    listaADevolver.add(new MinaDobleRadio(nodoDisparo));
                    break;
                default:
                    throw (new Error("Disparo no reconocido: " + nodoDisparo.getName()));
            }
        }
        return listaADevolver;
    }

    public ArrayList<VistaDaniador> construirVistasDaniadores(Element nodoDaniadores, ArrayList<Daniador> listaDaniadores, Dibujante dibujante) {
        Element nodoDisparo;
        VistaDisparoConvencional vistaDisparo;
        VistaMina vistaMina;
        ArrayList<VistaDaniador> listaADevolver = new ArrayList<VistaDaniador>();

        List<Element> listaNodosDisparo = nodoDaniadores.elements();
        for (int i = 0; i < listaDaniadores.size(); i++) {
            nodoDisparo = listaNodosDisparo.get(i);
            switch (nodoDisparo.getName()) {
                case "DisparoConvencional":
                    DisparoConvencional disparo = (DisparoConvencional) listaDaniadores.get(i);
                    vistaDisparo = new VistaDisparoConvencional(disparo);
                    listaADevolver.add(vistaDisparo);
                    disparo.agregarObservador(vistaDisparo);
                    dibujante.agregarDisparo(vistaDisparo);
                    break;
                case "MinaContacto":
                    MinaContacto minaContacto = (MinaContacto) listaDaniadores.get(i);
                    vistaMina = new VistaMina(minaContacto);
                    listaADevolver.add(vistaMina);
                    minaContacto.agregarObservador(vistaMina);
                    dibujante.agregarMina(vistaMina);
                    break;
                case "MinaRadio":
                    MinaRadio minaRadio = (MinaRadio) listaDaniadores.get(i);
                    vistaMina = new VistaMina(minaRadio);
                    listaADevolver.add(vistaMina);
                    minaRadio.agregarObservador(vistaMina);
                    dibujante.agregarMina(vistaMina);
                    break;
                case "MinaDobleRadio":
                    MinaDobleRadio minaDobleRadio = (MinaDobleRadio) listaDaniadores.get(i);
                    vistaMina = new VistaMina(minaDobleRadio);
                    listaADevolver.add(vistaMina);
                    minaDobleRadio.agregarObservador(vistaMina);
                    dibujante.agregarMina(vistaMina);
                    break;
                default:
                    throw (new Error("Disparo no reconocido: " + nodoDisparo.getName()));
            }

        }
        return listaADevolver;
    }
}
