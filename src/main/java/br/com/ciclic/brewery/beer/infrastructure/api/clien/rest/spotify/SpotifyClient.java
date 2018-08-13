package br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify;

import br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject.Playlist;
import br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject.Track;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpotifyClient {

    private final Logger log = LoggerFactory.getLogger(SpotifyClient.class);

    @Autowired
    private SpotifyApi spotifyApi;

    public Playlist findPlaylistsTracks(String name) {
        try {
            SearchPlaylistsRequest searchPlaylistsRequest = spotifyApi.searchPlaylists(name)
                                                                        .market(CountryCode.BR)
                                                                        .limit(1)
                                                                        .offset(0)
                                                                        .build();

            final Paging<PlaylistSimplified> playlist = searchPlaylistsRequest.execute();
            PlaylistSimplified[] playlistItems = playlist.getItems();

            if (playlistItems != null) {
                String idPlaylist = playlistItems[0].getId();
                String namePlaylist = playlistItems[0].getName();
                String idUser = playlistItems[0].getOwner().getId();

                Playlist to = new Playlist(namePlaylist);

                GetPlaylistsTracksRequest getPlaylistsTracksRequest = spotifyApi.getPlaylistsTracks(idUser, idPlaylist)
                                                                                .limit(10)
                                                                                .offset(0)
                                                                                .build();

                final Paging<PlaylistTrack> playlistTrack;

                try {
                    playlistTrack = getPlaylistsTracksRequest.execute();

                   List<Track> tracks =  Arrays.stream(playlistTrack.getItems())
                                                .map( p -> {
                                                    ArtistSimplified[] artists = p.getTrack().getArtists();
                                                    String nameTrack = p.getTrack().getName();
                                                    String artist = artists.length == 0 ? "" : artists[0].getName();
                                                    String url = p.getTrack().getHref();
                                                    return new Track(nameTrack, artist, url);
                                                })
                                                .collect(Collectors.toList());

                    to.setTracks(tracks);

                } catch (IOException | SpotifyWebApiException ex) {
                    log.error("No access api track spotify");
                }
                return to;
            }

        } catch (IOException | SpotifyWebApiException ex) {
            log.error("No access api playlist spotify");
        }

        return null;
    }
}
