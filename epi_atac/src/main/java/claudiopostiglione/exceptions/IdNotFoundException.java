package claudiopostiglione.exceptions;

import java.util.UUID;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(UUID id) {
        super("Errore, l'id " + id + " non è stato trovato");
    }
    public IdNotFoundException(long id) {
        super("Errore, l'id " + id + " non è stato trovato");
    }
}
