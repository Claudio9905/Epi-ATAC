package claudiopostiglione.exceptions;

public class emailUserNotFoundException extends RuntimeException {
    public emailUserNotFoundException(String message) {
        super(message);
    }
}
