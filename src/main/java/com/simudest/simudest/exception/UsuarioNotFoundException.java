package com.simudest.simudest.exception;

public final class UsuarioNotFoundException extends Exception {

    public UsuarioNotFoundException() {
        super();
    }

    public UsuarioNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UsuarioNotFoundException(final String message) {
        super(message);
    }

    public UsuarioNotFoundException(final Throwable cause) {
        super(cause);
    }

}
