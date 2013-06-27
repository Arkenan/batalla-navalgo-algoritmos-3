package barcos;

import org.dom4j.Element;

public interface Destructible {
    public abstract boolean estaDestruido();

    public abstract Element generarNodo();

}
