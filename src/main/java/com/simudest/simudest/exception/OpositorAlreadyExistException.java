package com.simudest.simudest.exception;

public final class OpositorAlreadyExistException extends RuntimeException {

    public OpositorAlreadyExistException() {
        super();
    }

    public OpositorAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public OpositorAlreadyExistException(final String message) {
        super(message);
    }

    public OpositorAlreadyExistException(final Throwable cause) {
        super(cause);
    }

}
