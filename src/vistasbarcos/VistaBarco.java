package vistasbarcos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import observador.ObjetoObservable;
import observador.Observador;

import org.dom4j.Element;

import barcos.Barco;
import constructoresdevistas.AbstractVistasBarcoFactory;
import constructoresdevistas.VistaLanchaFactory;
import constructoresdevistas.VistasBuqueFactory;
import constructoresdevistas.VistasDestructorFactory;
import constructoresdevistas.VistasPortaavionesFactory;
import constructoresdevistas.VistasRompehieloFactory;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class VistaBarco extends ObjetoObservable implements Observador, ObjetoDibujable {
    ArrayList<VistaParte> vistasPartes;
    Barco barco;

    public static ArrayList<AbstractVistasBarcoFactory> obetenerVistas(Element nodoPartida) {
        ArrayList<AbstractVistasBarcoFactory> listaFactories = new ArrayList<AbstractVistasBarcoFactory>();
        List<Element> listaNodosBarcos = nodoPartida.element("Barcos").elements();
        Iterator<Element> it = listaNodosBarcos.iterator();

        while (it.hasNext()) {
            Element nodoBarco = it.next();
            switch (nodoBarco.getName()) {
                case "Lancha":
                    listaFactories.add(new VistaLanchaFactory());
                    break;
                case "Buque":
                    listaFactories.add(new VistasBuqueFactory());
                    break;
                case "Destructor":
                    listaFactories.add(new VistasDestructorFactory());
                    break;
                case "Rompehielo":
                    listaFactories.add(new VistasRompehieloFactory());
                    break;
                case "Portaaviones":
                    listaFactories.add(new VistasPortaavionesFactory());
                    break;
                default:
                    throw new Error("Barco no reconocido: " + nodoBarco.getName());
            }
        }

        return listaFactories;
    }

    public VistaBarco(ArrayList<VistaParte> partes, Barco unBarco) {
        vistasPartes = partes;
        barco = unBarco;
        barco.agregarObservador(this);
        barco.obtenerEstrategiaDeMovimiento().agregarObservador(this);
    }

    public ArrayList<VistaParte> obtenerVistasPartes() {
        return vistasPartes;
    }

    public boolean estaDestruido() {
        return barco.estaDestruido();
    }

    @Override
    public void actualizar() {
        if (barco.estaDestruido()) {
            notificar();
        } else {
            for (int i = 0; i < vistasPartes.size(); i++) {
                vistasPartes.get(i).cambiarDireccion(barco.obtenerDireccionMovimiento());
            }
        }

    }

    @Override
    public void dibujar(SuperficieDeDibujo superficieDeDibujo) {
        for (int i = 0; i < vistasPartes.size(); i++) {
            vistasPartes.get(i).dibujar(superficieDeDibujo);
        }

    }

}
