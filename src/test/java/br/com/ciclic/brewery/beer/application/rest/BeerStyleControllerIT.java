package br.com.ciclic.brewery.beer.application.rest;

import br.com.ciclic.brewery.beer.BeerApplication;
import br.com.ciclic.brewery.beer.business.vo.BeerStyleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(classes = BeerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class BeerStyleControllerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApplicationContext context;

    private WebTestClient clientTest;

    @LocalServerPort
    private Integer port;

    @BeforeClass
    public void setUp() {
        clientTest = WebTestClient.bindToApplicationContext(context)
                .configureClient()
                .baseUrl("http://localhost:" + port)
                .build();
    }


    @Test(priority = 1)
    public void should_create_beer_style_with_sucess() throws Exception {

        BeerStyleVO beerStyle = new BeerStyleVO();

        this.clientTest
                .post().uri("/brewery/api/v1/beerstyless")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(BodyInserters.fromObject(beerStyle))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error").isEqualTo("O nome é de preenchimento obrigatório.");

    }
}