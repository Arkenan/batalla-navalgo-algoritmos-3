package juego;

import java.util.ArrayList;

import barcos.Barco;
import barcos.Buque;
import barcos.Destructor;
import barcos.Lancha;
import barcos.Portaaviones;
import barcos.Rompehielo;
import barcos.Vector;

public class ManejadorDeBarcos {

    private final ArrayList<Barco> barcos;

    public ManejadorDeBarcos() {
        barcos = new ArrayList<Barco>();
        this.crearBarcosConDireccionYSentidoAleatorio();
    }

    public void crearBarcosConDireccionYSentidoAleatorio() {
        Lancha lancha;
        Destructor destructor;
        Buque buque;
        Portaaviones portaaviones;
        Rompehielo rompehielo;

        for (int x = 1; x < 3; x++) {
            lancha = new Lancha(this.crearOrientacionAleatoria(), this.crearDireccionAleatoria());
            lancha.colocarEnTablero(crearPosicionAleatoria());
            barcos.add(lancha);

            destructor = new Destructor(this.crearOrientacionAleatoria(), this.crearDireccionAleatoria());
            barcos.add(destructor);
            destructor.colocarEnTablero(crearPosicionAleatoria());
        }

        portaaviones = new Portaaviones(this.crearOrientacionAleatoria(), this.crearDireccionAleatoria());
        barcos.add(portaaviones);
        portaaviones.colocarEnTablero(crearPosicionAleatoria());

        rompehielo = new Rompehielo(this.crearOrientacionAleatoria(), this.crearDireccionAleatoria());
        barcos.add(rompehielo);
        rompehielo.colocarEnTablero(crearPosicionAleatoria());

        buque = new Buque(this.crearOrientacionAleatoria(), this.crearDireccionAleatoria());
        barcos.add(buque);
        buque.colocarEnTablero(crearPosicionAleatoria());

    }

    private Vector crearPosicionAleatoria() {
        return new Vector((int) (Math.random() * 5) + 1, (int) (Math.random() * 5) + 1);
    }

    private Vector crearOrientacionAleatoria() {
        int x = (int) (Math.random() * 2);
        return new Vector(x, 1 - x);
    }

    private Vector crearDireccionAleatoria() {
        return new Vector((int) (Math.random() * 2), (int) (Math.random() * 2));
    }

    public int cantidadDeBarcos() {
        return barcos.size();
    }

    public int cantidadDeBarcosDestruidos() {
        int contador = 0;
        for (int x = 0; x < barcos.size(); x++) {
            if ((barcos.get(x)).estaDestruido()) {
                contador++;
            }
        }
        return contador;

    }

    public void colocarBarcos() {

    }

    public void moverBarcos() {
        for (int x = 0; x < barcos.size(); x++) {
            (barcos.get(x)).moverse();
        }

    }

    public ArrayList<Barco> obtenerBarcos() {
        return barcos;
    }

    public boolean todosLosBarcosEstanDestruidos() {
        return (this.cantidadDeBarcosDestruidos()) == (this.cantidadDeBarcos());
    }
}
