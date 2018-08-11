package br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Track implements Serializable {
    private static final long serialVersionUID = -3207475590502281521L;

    private String name;
    private String artist;
    private String link;

}
