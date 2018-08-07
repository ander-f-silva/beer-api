package br.com.ciclic.brewery.beer.controller;


import br.com.ciclic.brewery.beer.BeerApplication;
import br.com.ciclic.brewery.beer.controller.vo.BeerStyleVO;
import br.com.ciclic.brewery.beer.controller.vo.TemperatureVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeerStyleControllerIT {
    @LocalServerPort
    private Integer port;

    private TestRestTemplate restTemplate =  new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void should_create_beer_style_with_sucess() {
        HttpEntity<BeerStyleVO> entity = new HttpEntity<>(new BeerStyleVO("Weissbier", new TemperatureVO(2, 2)), headers);
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles", HttpMethod.POST, entity, Void.class);
        String location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(location.contains("/brewery/api/v1/beerstyles"));
    }

    @Test
    public void should_create_beer_style_with_field_name_null() {
        HttpEntity<BeerStyleVO> entity = new HttpEntity<>(new BeerStyleVO(null, new TemperatureVO(2, 2)), headers);
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles", HttpMethod.POST, entity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void should_create_beer_style_with_field_name_empty() {
        HttpEntity<BeerStyleVO> entity = new HttpEntity<>(new BeerStyleVO("", new TemperatureVO(2, 2)), headers);
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles", HttpMethod.POST, entity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void should_create_beer_style_with_field_maximum_null() {
        HttpEntity<BeerStyleVO> entity = new HttpEntity<>(new BeerStyleVO("Weissbier", new TemperatureVO(null, 2)), headers);
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles", HttpMethod.POST, entity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void should_create_beer_style_with_field_manimum_null() throws Exception {
        HttpEntity<BeerStyleVO> entity = new HttpEntity<>(new BeerStyleVO("Weissbier", new TemperatureVO(2, null)), headers);
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles", HttpMethod.POST, entity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}