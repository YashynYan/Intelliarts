package yanyashyn.demo.exceptions;

public class WrongInputDataException extends Exception{
    public WrongInputDataException() {
    }

    public WrongInputDataException(String message) {
        super(message);
    }
}
