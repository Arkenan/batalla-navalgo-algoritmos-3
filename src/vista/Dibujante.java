package vista;

import java.util.ArrayList;

import vistasbarcos.VistaBarco;
import fiuba.algo3.titiritero.modelo.ObjetoDibujable;
import fiuba.algo3.titiritero.modelo.SuperficieDeDibujo;

public class Dibujante implements ObjetoDibujable {

    ArrayList<ObjetoDibujable> dibujables;

    public Dibujante(ObjetoDibujable fondo) {
        dibujables = new ArrayList<ObjetoDibujable>();
        dibujables.add(fondo);
    }

    public void cambiarFondo(ObjetoDibujable fondo) {
        dibujables.remove(0);
        dibujables.add(0, fondo);
    }

    public void agregarDisparo(ObjetoDibujable disparo) {
        dibujables.add(dibujables.size(), disparo);
    }

    public void agregarMina(ObjetoDibujable mina) {
        dibujables.add(1, mina);
    }

    public void remover(ObjetoDibujable dibujable) {
        dibujables.remove(dibujable);
    }

    public void cambiarAlTope(ObjetoDibujable dibujable) {
        if (dibujables.contains(dibujable)) {
            dibujables.remove(dibujable);
            dibujables.add(dibujables.size(), dibujable);
        }
    }

    @Override
    public void dibujar(SuperficieDeDibujo superficieDeDibujo) {
        for (int i = 0; i < dibujables.size(); i++) {
            dibujables.get(i).dibujar(superficieDeDibujo);
        }
    }

    public void agregarVistaBarco(VistaBarco vistaBarco) {
        dibujables.add(vistaBarco);

    }
}