package br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpotifyClient {

    private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
                                                                .setAccessToken(accessToken)
                                                                .build();

    public void getPlaylistsTracks_Sync(String name) {
        try {
            SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(name)
                                                                .market(CountryCode.SE)
                                                                .limit(10)
                                                                .offset(0)
                                                                .build();

            final Paging<Track> trackPaging = searchTracksRequest.execute();

            Track[] tracks =  trackPaging.getItems();

            for(Track t : tracks) {
                System.out.println(t.getArtists());
                System.out.println(t.getAlbum());
                System.out.println(t.getExternalUrls());
            }

        } catch (IOException | SpotifyWebApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
