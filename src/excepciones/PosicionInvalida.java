package excepciones;

public class PosicionInvalida extends RuntimeException {
    public PosicionInvalida(String msg) {
        super(msg);
    }
}
