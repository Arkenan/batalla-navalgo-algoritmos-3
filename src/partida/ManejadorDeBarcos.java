package partida;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import movimientostrategyfactory.AbstractMovimientoFactory;
import movimientostrategyfactory.MovimientoLinealFactory;

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

public class ManejadorDeBarcos {

    protected ArrayList<Movible> elementosAMoverPorTurno;
    protected ArrayList<Destructible> elementosADestruirParaGanar;
    final ArrayList<AbstractMovimientoFactory> constructoresDeMovimientosPosibles;

    public ManejadorDeBarcos() {
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

    public void crearBarcos(Element nodoBarcos) {
        // primero busco la lista de nodos de barcos. Primero para barcos completos.
        ArrayList<Barco> ListaBarcos = new ArrayList<Barco>();
        List<Element> listaNodosBarcos = nodoBarcos.elements();
        Iterator<Element> it = listaNodosBarcos.iterator();
        while (it.hasNext()) {
            Element nodoBarco = it.next();

            // busco la estrategia.
            MovimientoStrategy estrategiaActual;
            Element nodoEstrategia = nodoBarco.element("estrategia");
            Vector direccionActual = new Vector(nodoEstrategia.attributeValue("direccionActual"));
            switch (nodoEstrategia.getName()) {
                case "MovimientoLinealStrategy":
                    estrategiaActual = new MovimientoLinealStrategy(direccionActual);
                    break;
                default:
                    throw new Error("No se reconoce la estrategia " + nodoEstrategia.getName());
            }

            // busco el barco.
            Barco barcoActual;
            Vector orientacionActual = new Vector(nodoBarco.attributeValue("orientacion"));
            switch (nodoBarco.getName()) {
                case "Lancha":
                    barcoActual = new Lancha(orientacionActual, estrategiaActual);
                    break;
                case "Buque":
                    barcoActual = new Buque(orientacionActual, estrategiaActual);
                    break;
                case "Destructor":
                    barcoActual = new Destructor(orientacionActual, estrategiaActual);
                    break;
                case "Rompehielo":
                    barcoActual = new Rompehielo(orientacionActual, estrategiaActual);
                    break;
                case "Portaaviones":
                    barcoActual = new Portaaviones(orientacionActual, estrategiaActual);
                    break;
                default:
                    throw new Error("Barco no reconocido: " + nodoBarco.getName());
            }
            elementosAMoverPorTurno.add(barcoActual);
            elementosADestruirParaGanar.add(barcoActual);
        }

    }
}
