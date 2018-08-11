package br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject;

import java.io.Serializable;

public class Track implements Serializable {
    private static final long serialVersionUID = -3207475590502281521L;

    private String name;
    private String artist;
    private String link;

    public Track(String name, String artist, String link) {
        this.name = name;
        this.artist = artist;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
