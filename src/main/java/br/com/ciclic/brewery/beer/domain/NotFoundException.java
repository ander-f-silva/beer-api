package br.com.ciclic.brewery.beer.domain;

public class NotFoundException extends Exception {

    public NotFoundException(String message) {
        super(message);
    }
}
