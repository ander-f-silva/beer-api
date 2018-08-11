package br.com.ciclic.brewery.beer.application.transferobject;

import lombok.Data;

import java.io.Serializable;

@Data
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
}
