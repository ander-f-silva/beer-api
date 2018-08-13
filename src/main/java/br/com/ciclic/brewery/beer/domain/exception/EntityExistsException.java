package br.com.ciclic.brewery.beer.domain.exception;

public class EntityExistsException extends Exception {

    public EntityExistsException(String message) {
        super(message);
    }
}
