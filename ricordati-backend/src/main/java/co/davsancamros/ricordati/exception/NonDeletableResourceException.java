package co.davsancamros.ricordati.exception;

public class NonDeletableResourceException extends RuntimeException {
    public NonDeletableResourceException(String message) {
        super(message);
    }
}
