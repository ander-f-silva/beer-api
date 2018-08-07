package br.com.ciclic.brewery.beer.controller;

import br.com.ciclic.brewery.beer.controller.vo.BeerStyleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/brewery/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeerStyleController {

    @Autowired
    private HttpRequest request;

    @PostMapping(value = "/beerstyles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@Valid @RequestBody BeerStyleVO beerStyle) {


        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(1).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/beerstyles/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/beerstyles/{id}")
    public ResponseEntity<BeerStyleVO> find(@PathVariable("id")  UUID id) {
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/beerstyles")
    public ResponseEntity<BeerStyleVO> findAll(@PathVariable("id")  UUID id) {
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "/beerstyles/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") UUID id, @Valid @RequestBody BeerStyleVO beerStyle) {
        return ResponseEntity.noContent().build();
    }

}
