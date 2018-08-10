package br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify;

import br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject.Playlist;
import br.com.ciclic.brewery.beer.infrastructure.api.clien.rest.spotify.valueobject.Track;
import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.PlaylistSimplified;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistsTracksRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchPlaylistsRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
public class SpotifyClient {

  private static final SpotifyApi spotifyApi =
      new SpotifyApi.Builder()
          .setClientId("03c20df0a9ff48cabdc1f1963774066d")
          .setClientSecret("9c0b4ca781364c78a8c7766194b99675")
          .setRedirectUri(URI.create("http://localhost:8080/callback"))
          .build();

  private static final ClientCredentialsRequest clientCredentialsRequest =
      spotifyApi.clientCredentials().build();

  public Playlist findPlaylistsTracks(String name) {
    try {
      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

      spotifyApi.setAccessToken(clientCredentials.getAccessToken());

      SearchPlaylistsRequest searchPlaylistsRequest = spotifyApi.searchPlaylists(name).market(CountryCode.BR).limit(1).offset(0).build();

      final Paging<PlaylistSimplified> playlist = searchPlaylistsRequest.execute();

      PlaylistSimplified[] playlistItems = playlist.getItems();

      Playlist to = new Playlist(playlistItems[0].getName());

      GetPlaylistsTracksRequest getPlaylistsTracksRequest =  spotifyApi.getPlaylistsTracks(playlistItems[0].getOwner().getId(), playlistItems[0].getId()).build();

      final Paging<PlaylistTrack> playlistTrack;

      try {
           playlistTrack = getPlaylistsTracksRequest.execute();

          Track track;
            for (PlaylistTrack laylistTrack : playlistTrack.getItems()) {
                ArtistSimplified[] artists = laylistTrack.getTrack().getArtists();

                String nameTrack = laylistTrack.getTrack().getName();
                String artist = artists.length == 0 ? "" : artists[0].getName();
                String url = laylistTrack.getTrack().getHref();
                to.add(new Track(nameTrack, artist, url));
            }
      } catch (IOException e1) {
        e1.printStackTrace();
      } catch (SpotifyWebApiException e1) {
        e1.printStackTrace();
      }

    } catch (IOException | SpotifyWebApiException ex) {
      System.out.println("Error: " + ex.getMessage());
    }
      return null;
  }
}
