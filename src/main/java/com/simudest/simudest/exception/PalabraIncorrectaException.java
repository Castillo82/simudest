package com.simudest.simudest.exception;

public final class PalabraIncorrectaException extends RuntimeException {

    public PalabraIncorrectaException() {
        super();
    }

    public PalabraIncorrectaException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public PalabraIncorrectaException(final String message) {
        super(message);
    }

    public PalabraIncorrectaException(final Throwable cause) {
        super(cause);
    }

}
