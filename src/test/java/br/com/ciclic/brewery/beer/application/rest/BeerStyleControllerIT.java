package br.com.ciclic.brewery.beer.application.rest;

import br.com.ciclic.brewery.beer.BeerApplication;
import br.com.ciclic.brewery.beer.business.vo.BeerStyleVO;
import br.com.ciclic.brewery.beer.business.vo.TemperatureVO;
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

        BeerStyleVO beerStyle = new BeerStyleVO("Weissbier", new TemperatureVO(2, 2));

        this.clientTest
                .post().uri("/brewery/api/v1/beerstyles")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(BodyInserters.fromObject(beerStyle))
                .exchange()
                .expectStatus().isCreated();

    }

    @Test(priority = 2)
    public void should_create_beer_style_with_field_name_null() throws Exception {

        BeerStyleVO beerStyle = new BeerStyleVO(null, new TemperatureVO(2, 2));

        this.clientTest
                .post().uri("/brewery/api/v1/beerstyles")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(BodyInserters.fromObject(beerStyle))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error", "Field name is required");

    }

    @Test(priority = 3)
    public void should_create_beer_style_with_field_name_empty() throws Exception {

        BeerStyleVO beerStyle = new BeerStyleVO("", new TemperatureVO(2, 2));

        this.clientTest
                .post().uri("/brewery/api/v1/beerstyles")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(BodyInserters.fromObject(beerStyle))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error", "Field name is required");

    }

    @Test(priority = 4)
    public void should_create_beer_style_with_field_maximum_null() throws Exception {

        BeerStyleVO beerStyle = new BeerStyleVO("Weissbier", new TemperatureVO(null, 2));

        this.clientTest
                .post().uri("/brewery/api/v1/beerstyles")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(BodyInserters.fromObject(beerStyle))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error", "Field name is required");

    }

    @Test(priority = 5)
    public void should_create_beer_style_with_field_manimum_null() throws Exception {

        BeerStyleVO beerStyle = new BeerStyleVO("Weissbier", new TemperatureVO(2, null));

        this.clientTest
                .post().uri("/brewery/api/v1/beerstyles")
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(BodyInserters.fromObject(beerStyle))
                .exchange()
                .expectStatus().is4xxClientError()
                .expectBody()
                .jsonPath("$.error", "Field name is required");

    }
    


}