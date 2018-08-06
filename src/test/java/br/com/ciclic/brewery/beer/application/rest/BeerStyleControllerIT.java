package br.com.ciclic.brewery.beer.application.rest;

import br.com.ciclic.brewery.beer.business.vo.BeerStyleVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;


@RunWith(SpringRunner.class)
@WebFluxTest
public class BeerStyleControllerIT {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void should_create_beer_style_with_sucess() throws Exception {

        webClient.post().uri("/api/customer/post")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(new BeerStyleVO()))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(String.class)
                .isEqualTo("Post Successfully!");
    }
}