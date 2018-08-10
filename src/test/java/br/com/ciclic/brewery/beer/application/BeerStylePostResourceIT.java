package br.com.ciclic.brewery.beer.application;

import br.com.ciclic.brewery.beer.BeerApplication;
import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.TemperatureTransferObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeerStylePostResourceIT {
    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void shouldCreateBeerStyleWithSuccess() {
        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(new BeerStyleTransferObject("Weissbier", new TemperatureTransferObject(2, 2)), headers);
        ResponseEntity<Void> response = restTemplate.exchange("/brewery/api/v1/beerstyles", HttpMethod.POST, entity, Void.class);
        String location = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(location.contains("/brewery/api/v1/beerstyles"));
    }

    @Test
    public void shouldCreateBeerStyleWithFieldNameNull() {
        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(new BeerStyleTransferObject(null, new TemperatureTransferObject(2, 2)), headers);
        ResponseEntity<Void> response = restTemplate.exchange("/brewery/api/v1/beerstyles", HttpMethod.POST, entity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldCreateBeerStyleWithFieldNameEmpty() {
        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(new BeerStyleTransferObject("", new TemperatureTransferObject(2, 2)), headers);
        ResponseEntity<Void> response = restTemplate.exchange("/brewery/api/v1/beerstyles", HttpMethod.POST, entity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldCreateBeerStyleWithFieldMaximumNull() {
        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(new BeerStyleTransferObject("Weissbier", new TemperatureTransferObject(null, 2)), headers);
        ResponseEntity<Void> response = restTemplate.exchange("/brewery/api/v1/beerstyles", HttpMethod.POST, entity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldCreateBeerStyleWithFieldMinimumNull() throws Exception {
        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(new BeerStyleTransferObject("Weissbier", new TemperatureTransferObject(2, null)), headers);
        ResponseEntity<Void> response = restTemplate.exchange("/brewery/api/v1/beerstyles", HttpMethod.POST, entity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void shouldCreateBeerStyleWithFieldMaximumLessMinimum() throws Exception {
        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(new BeerStyleTransferObject("Weissbier", new TemperatureTransferObject(2, 3)), headers);
        ResponseEntity<Void> response = restTemplate.exchange("/brewery/api/v1/beerstyles", HttpMethod.POST, entity, Void.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}