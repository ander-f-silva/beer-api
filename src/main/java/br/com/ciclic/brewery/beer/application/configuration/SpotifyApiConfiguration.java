package br.com.ciclic.brewery.beer.application.configuration;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;

@Configuration
public class SpotifyApiConfiguration {

    @Value("${spotify.authentication.clientid}")
    private String clientId;

    @Value("${spotify.authentication.clientsecret}")
    private String clientSecret;

    @Value("${spotify.authentication.redirecturi}")
    private String redirecturi;

    @Bean
    public SpotifyApi spotifyApi() throws IOException, SpotifyWebApiException {

        SpotifyApi spotifyApi =  new SpotifyApi.Builder()
                                                .setClientId(clientId)
                                                .setClientSecret(clientSecret)
                                                .setRedirectUri(URI.create(redirecturi))
                                                .build();

        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());

        return spotifyApi;

    }
}
