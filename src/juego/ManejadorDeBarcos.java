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
        Vector posicion = new Vector(0, 0);
        Vector orientacion = new Vector(0, 0);
        Vector direccion = new Vector(0, 0);
        Lancha lancha;
        Destructor destructor;
        Buque buque;
        Portaaviones portaaviones;
        Rompehielo rompehielo;

        for (int x = 1; x < 3; x++) {
            this.crearParametrosAleatoriosDeBarco(orientacion, posicion, direccion);
            lancha = new Lancha(orientacion, posicion, direccion);
            barcos.add(lancha);
            this.crearParametrosAleatoriosDeBarco(orientacion, posicion, direccion);
            destructor = new Destructor(orientacion, posicion, direccion);
            barcos.add(destructor);
        }

        this.crearParametrosAleatoriosDeBarco(orientacion, posicion, direccion);
        portaaviones = new Portaaviones(orientacion, posicion, direccion);
        barcos.add(portaaviones);

        this.crearParametrosAleatoriosDeBarco(orientacion, posicion, direccion);
        rompehielo = new Rompehielo(orientacion, posicion, direccion);
        barcos.add(rompehielo);

        this.crearParametrosAleatoriosDeBarco(orientacion, posicion, direccion);
        buque = new Buque(orientacion, posicion, direccion);
        barcos.add(buque);

    }

    private void crearParametrosAleatoriosDeBarco(Vector orientacion, Vector posicion, Vector direccion) {
        posicion.setX((int) (Math.random() * 5) + 3);
        posicion.setY((int) (Math.random() * 5) + 3);
        orientacion.setX((int) (Math.random() * 2));
        orientacion.setY(1 - orientacion.x());
        direccion.setX((int) (Math.random() * 2));
        direccion.setY((int) (Math.random() * 2));
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
