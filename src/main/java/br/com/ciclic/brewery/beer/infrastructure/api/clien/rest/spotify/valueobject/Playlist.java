package br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Playlist implements Serializable {
    private static final long serialVersionUID = 5322005449888476018L;

    public String name;

    public List<Track> tracks;

    public Playlist(String name) {
        this.name = name;
        this.tracks = new ArrayList<>();
    }

    public void add(Track track) {
        tracks.add(track);
    }
}
