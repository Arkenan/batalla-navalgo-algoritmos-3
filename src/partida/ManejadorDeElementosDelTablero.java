package partida;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import movimientostrategyfactory.AbstractMovimientoFactory;
import movimientostrategyfactory.MovimientoLinealFactory;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import barcofactory.AbstractBarcoFactory;
import barcofactory.BuqueFactory;
import barcofactory.DestructorFactory;
import barcofactory.LanchaFactory;
import barcofactory.PortaavionesFactory;
import barcofactory.RompehieloFactory;
import barcos.Barco;
import barcos.Buque;
import barcos.Destructible;
import barcos.Destructor;
import barcos.Lancha;
import barcos.Movible;
import barcos.Portaaviones;
import barcos.Rompehielo;
import barcos.Vector;
import barcos.strategies.MovimientoLinealStrategy;
import barcos.strategies.MovimientoStrategy;

public class ManejadorDeElementosDelTablero {

    protected ArrayList<Movible> elementosAMoverPorTurno;
    protected ArrayList<Destructible> elementosADestruirParaGanar;
    final ArrayList<AbstractMovimientoFactory> constructoresDeMovimientosPosibles;

    public ManejadorDeElementosDelTablero() {
        elementosAMoverPorTurno = new ArrayList<Movible>();
        elementosADestruirParaGanar = new ArrayList<Destructible>();
        constructoresDeMovimientosPosibles = new ArrayList<AbstractMovimientoFactory>();
        constructoresDeMovimientosPosibles.add(new MovimientoLinealFactory());
    }

    public ArrayList<Barco> crearBarcosPorDefault() {
        ArrayList<AbstractBarcoFactory> factorysDeBarco = new ArrayList<AbstractBarcoFactory>();
        for (int i = 0; i < 2; i++) {
            factorysDeBarco.add(new LanchaFactory());
            factorysDeBarco.add(new DestructorFactory());
        }
        factorysDeBarco.add(new BuqueFactory());
        factorysDeBarco.add(new PortaavionesFactory());
        factorysDeBarco.add(new RompehieloFactory());
        return crearBarcosConParametrosAleatoriosDeLosConstructores(factorysDeBarco);
    }

    public ArrayList<Barco> crearBarcosConParametrosAleatoriosDeLosConstructores(ArrayList<AbstractBarcoFactory> factorysDeBarco) {
        AbstractBarcoFactory factoryDeBarcoAuxiliar;
        ArrayList<Barco> barcosCreados = new ArrayList<Barco>();
        Barco barcoAuxiliar;
        for (int i = 0; i < factorysDeBarco.size(); i++) {
            factoryDeBarcoAuxiliar = factorysDeBarco.get(i);
            barcoAuxiliar = factoryDeBarcoAuxiliar.crearBarco(this.crearOrientacionAleatoria(), this.crearMovimientoAleatorio());
            elementosAMoverPorTurno.add(barcoAuxiliar);
            elementosADestruirParaGanar.add(barcoAuxiliar);
            barcoAuxiliar.colocarEnTablero(this.crearPosicionAleatoria());
            barcosCreados.add(barcoAuxiliar);
        }
        return barcosCreados;
    }

    public int cantidadDeElementosADestruirParaGanar() {
        return elementosADestruirParaGanar.size();
    }

    public int cantidadDeElementosDestruidos() {
        int contador = 0;
        for (int x = 0; x < elementosADestruirParaGanar.size(); x++) {
            if ((elementosADestruirParaGanar.get(x)).estaDestruido()) {
                contador++;
            }
        }
        return contador;

    }

    public void moverElementos() {
        for (int x = 0; x < elementosAMoverPorTurno.size(); x++) {
            (elementosAMoverPorTurno.get(x)).moverse();
        }

    }

    public boolean todosLosDestructiblesEstanDestruidos() {
        return (this.cantidadDeElementosDestruidos()) == (this.cantidadDeElementosADestruirParaGanar());
    }

    // ----------------- Metodos Privados --------------------------------

    private Vector crearPosicionAleatoria() {
        return new Vector((int) (Math.random() * 4) + 1, (int) (Math.random() * 4) + 1);
    }

    private Vector crearOrientacionAleatoria() {
        int x = (int) (Math.random() * 2);
        return new Vector(x, 1 - x);
    }

    private MovimientoStrategy crearMovimientoAleatorio() {
        int x = (int) (Math.random() * 2);
        Vector direccion;
        if (x == 0)
            direccion = new Vector(x, 1);
        else {
            direccion = new Vector(x, (int) (Math.random() * 2));
        }
        int posicionAleatoria = (int) (Math.random() * (constructoresDeMovimientosPosibles.size()));
        AbstractMovimientoFactory constructorAleatorio = constructoresDeMovimientosPosibles.get(posicionAleatoria);
        return constructorAleatorio.crearMovimientoStrategy(direccion);
    }

    public ArrayList<Barco> crearBarcos(Element nodoBarcos) {
        // primero busco la lista de nodos de barcos. Primero para barcos completos.
        ArrayList<Barco> ListaBarcos = new ArrayList<Barco>();
        List<Element> listaNodosBarcos = nodoBarcos.elements();
        Iterator<Element> it = listaNodosBarcos.iterator();

        while (it.hasNext()) {
            Element nodoBarco = it.next();

            // busco la estrategia.
            MovimientoStrategy estrategiaActual;
            Element nodoEstrategia = nodoBarco.element("Estrategia");
            Vector direccionActual = new Vector(nodoEstrategia.attributeValue("direccionActual"));
            switch (nodoEstrategia.attributeValue("tipo")) {
                case "MovimientoLinealStrategy":
                    estrategiaActual = new MovimientoLinealStrategy(direccionActual);
                    break;
                default:
                    throw new Error("No se reconoce la estrategia " + nodoEstrategia.attributeValue("tipo"));
            }

            // busco el barco.
            Barco barcoActual;
            Vector orientacionActual = new Vector(nodoBarco.attributeValue("orientacion"));
            switch (nodoBarco.getName()) {
                case "Lancha":
                    if (nodoBarco.attributeValue("completo").equals("si")) {
                        barcoActual = new Lancha(orientacionActual, estrategiaActual);
                    } else {
                        barcoActual = new Lancha(nodoBarco, estrategiaActual);
                    }
                    break;
                case "Buque":
                    if (nodoBarco.attributeValue("completo").equals("si")) {
                        barcoActual = new Buque(orientacionActual, estrategiaActual);
                    } else {
                        barcoActual = new Buque(nodoBarco, estrategiaActual);
                    }
                    break;
                case "Destructor":
                    if (nodoBarco.attributeValue("completo").equals("si")) {
                        barcoActual = new Destructor(orientacionActual, estrategiaActual);
                    } else {
                        barcoActual = new Destructor(nodoBarco, estrategiaActual);
                    }
                    break;
                case "Rompehielo":
                    if (nodoBarco.attributeValue("completo").equals("si")) {
                        barcoActual = new Rompehielo(orientacionActual, estrategiaActual);
                    } else {
                        barcoActual = new Rompehielo(nodoBarco, estrategiaActual);
                    }
                    break;
                case "Portaaviones":
                    if (nodoBarco.attributeValue("completo").equals("si")) {
                        barcoActual = new Portaaviones(orientacionActual, estrategiaActual);
                    } else {
                        barcoActual = new Portaaviones(nodoBarco, estrategiaActual);
                    }
                    break;
                default:
                    throw new Error("Barco no reconocido: " + nodoBarco.getName());
            }
            ListaBarcos.add(barcoActual);
            barcoActual.colocarEnTablero(new Vector(nodoBarco.attributeValue("posicion")));
            elementosAMoverPorTurno.add(barcoActual);
            elementosADestruirParaGanar.add(barcoActual);
        }

        return ListaBarcos;
    }

    public Element generarNodoBarcos() {
        Element nodoADevolver = DocumentHelper.createElement("Barcos");
        // PROVISORIO
        Iterator<Destructible> it = elementosADestruirParaGanar.iterator();
        while (it.hasNext()) {
            Destructible barcoActual = it.next();
            nodoADevolver.add(barcoActual.generarNodo());
        }
        return nodoADevolver;
    }
}
