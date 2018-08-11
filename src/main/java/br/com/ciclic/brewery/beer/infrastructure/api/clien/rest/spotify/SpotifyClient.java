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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class SpotifyClient {

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

            String idPlaylist =playlistItems[0].getId();
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

                for (PlaylistTrack laylistTrack : playlistTrack.getItems()) {
                    ArtistSimplified[] artists = laylistTrack.getTrack().getArtists();
                    String nameTrack = laylistTrack.getTrack().getName();
                    String artist = artists.length == 0 ? "" : artists[0].getName();
                    String url = laylistTrack.getTrack().getHref();
                    to.add(new Track(nameTrack, artist, url));
                }
            } catch (IOException | SpotifyWebApiException ex) {
                log.error("No access api track spotify");
            }

            return to;

        } catch (IOException | SpotifyWebApiException ex) {
            log.error("No access api playlist spotify");
        }

        return null;
    }
}
