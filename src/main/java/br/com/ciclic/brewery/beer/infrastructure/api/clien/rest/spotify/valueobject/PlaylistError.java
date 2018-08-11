package br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PlaylistError implements Serializable {
    private static final long serialVersionUID = -5228311394852247443L;

    public Integer code;

    public String message;

    public PlaylistError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
