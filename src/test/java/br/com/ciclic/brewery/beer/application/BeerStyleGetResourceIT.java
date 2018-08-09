package br.com.ciclic.brewery.beer.application;

import br.com.ciclic.brewery.beer.BeerApplication;
import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.BreweryTransferObject;
import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;
import br.com.ciclic.brewery.beer.infrastructure.repository.BeerStyleRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BeerStyleGetResourceIT {
    @LocalServerPort
    private Integer port;

    @Autowired
    private BeerStyleRepository repository;

    private TestRestTemplate restTemplate =  new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void shouldGetOneBeerStyleWithSuccess() {
        BeerStyle styleEntity = BeerStyle.builder().name("Weissbier").maximum(1).minimum(1).build();
        styleEntity = repository.insert(styleEntity);
        String id = styleEntity.getId();

        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(headers);
        ResponseEntity<BeerStyleTransferObject> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles/" + id, HttpMethod.GET, entity, BeerStyleTransferObject.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Weissbier", response.getBody().getName());
        assertEquals(Integer.valueOf(1), response.getBody().getTemperature().getMaximum());
        assertEquals(Integer.valueOf(1), response.getBody().getTemperature().getMinimum());
    }

    @Test
    public void shouldGetOneBeerStyleNotNound() {
        String id = "53535234523452345234523452345234523";

        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(headers);
        ResponseEntity<BeerStyleTransferObject> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles/" + id, HttpMethod.GET, entity, BeerStyleTransferObject.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldGetAllBeerStyleWithSuccess() {
        BeerStyle styleEntityOne = BeerStyle.builder().name("Weissbier").maximum(1).minimum(1).build();
        repository.insert(styleEntityOne);

        BeerStyle styleEntityTwo = BeerStyle.builder().name("Pilsens").maximum(2).minimum(2).build();
        repository.insert(styleEntityTwo);

        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(headers);
        ResponseEntity<BreweryTransferObject> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles", HttpMethod.GET, entity, BreweryTransferObject.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals("Weissbier", response.getBody().getList().get(0).getName());
        assertEquals(Integer.valueOf(1), response.getBody().getList().get(0).getTemperature().getMaximum());
        assertEquals(Integer.valueOf(1), response.getBody().getList().get(0).getTemperature().getMinimum());

        assertEquals("Pilsens", response.getBody().getList().get(1).getName());
        assertEquals(Integer.valueOf(2), response.getBody().getList().get(1).getTemperature().getMaximum());
        assertEquals(Integer.valueOf(2), response.getBody().getList().get(1).getTemperature().getMinimum());
    }

    @Test
    public void shouldGetAllBeerStyleWithNotFound() {
        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(headers);
        ResponseEntity<BreweryTransferObject> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles", HttpMethod.GET, entity, BreweryTransferObject.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldGetBeerStyleSearchByTemperature() {
        BeerStyle styleEntityOne = BeerStyle.builder().name("Weissbier").maximum(3).minimum(-1).build();
        repository.insert(styleEntityOne);

        BeerStyle styleEntityTwo = BeerStyle.builder().name("Dunkel").maximum(2).minimum(-8).build();
        repository.insert(styleEntityTwo);

        int temperature = -2;

        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(headers);

        ResponseEntity<BreweryTransferObject> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles/temperatures/" + temperature, HttpMethod.GET, entity, BreweryTransferObject.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Dunkel", response.getBody().getList().get(1).getName());
        assertEquals(Integer.valueOf(2), response.getBody().getList().get(1).getTemperature().getMaximum());
        assertEquals(Integer.valueOf(-8), response.getBody().getList().get(1).getTemperature().getMinimum());
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

}