package model.exceptions;

// Exception thrown if name input is null
public class InvalidNameException extends Exception {

    public InvalidNameException(String msg) {
        super(msg);
    }
}
