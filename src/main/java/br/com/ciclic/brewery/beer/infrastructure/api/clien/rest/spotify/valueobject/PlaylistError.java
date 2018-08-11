package br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject;

import java.io.Serializable;

public class PlaylistError implements Serializable {
    private static final long serialVersionUID = -5228311394852247443L;

    public Integer code;

    public String message;

    public PlaylistError(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
