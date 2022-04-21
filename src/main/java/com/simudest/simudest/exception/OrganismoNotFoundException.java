package com.simudest.simudest.exception;

public final class OrganismoNotFoundException extends Exception {

    public OrganismoNotFoundException() {
        super();
    }

    public OrganismoNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public OrganismoNotFoundException(final String message) {
        super(message);
    }

    public OrganismoNotFoundException(final Throwable cause) {
        super(cause);
    }

}
