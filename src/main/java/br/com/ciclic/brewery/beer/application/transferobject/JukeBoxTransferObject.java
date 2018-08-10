package br.com.ciclic.brewery.beer.application.transferobject;

import br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject.Playlist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JukeBoxTransferObject implements Serializable {
    private static final long serialVersionUID = 4327176708016739498L;

    private String beerStyle;

    private Playlist playlist;

}
