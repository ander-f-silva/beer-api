package br.com.ciclic.brewery.beer.application.transferobject;

import java.io.Serializable;

public class JukeBoxTransferObject<T> implements Serializable {
    private static final long serialVersionUID = 4327176708016739498L;

    private String beerStyle;

    private T playlist;

    public JukeBoxTransferObject(String beerStyle, T playlist) {
        this.beerStyle = beerStyle;
        this.playlist = playlist;
    }

    public JukeBoxTransferObject() {
    }

    public String getBeerStyle() {
        return beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public T getPlaylist() {
        return playlist;
    }

    public void setPlaylist(T playlist) {
        this.playlist = playlist;
    }
}
