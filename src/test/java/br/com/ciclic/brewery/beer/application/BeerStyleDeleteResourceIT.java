package br.com.ciclic.brewery.beer.application;


import br.com.ciclic.brewery.beer.BeerApplication;
import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;
import br.com.ciclic.brewery.beer.infrastructure.repository.BeerStyleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BeerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.yml")
public class BeerStyleDeleteResourceIT {
    @LocalServerPort
    private Integer port;

    @Autowired
    private BeerStyleRepository repository;

    private TestRestTemplate restTemplate =  new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void shouldDeleteBeerStyleWithSuccess() {
        BeerStyle styleEntity = new BeerStyle("Weissbier", 1, 1);
        styleEntity = repository.insert(styleEntity);
        String id = styleEntity.getId();

        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(headers);
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles/" + id, HttpMethod.DELETE, entity, Void.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void shouldDeleteBeerStyleNotFound() {
        String id = "53535234523452345234523452345234523";

        HttpEntity<BeerStyleTransferObject> entity = new HttpEntity<>(headers);
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:" + port + "/brewery/api/v1/beerstyles/" + id, HttpMethod.DELETE, entity, Void.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}