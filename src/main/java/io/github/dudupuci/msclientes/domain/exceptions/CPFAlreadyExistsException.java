package io.github.dudupuci.msclientes.domain.exceptions;

public class CPFAlreadyExistsException extends RuntimeException {

    public CPFAlreadyExistsException(String message) {
        super(message);
    }
}
