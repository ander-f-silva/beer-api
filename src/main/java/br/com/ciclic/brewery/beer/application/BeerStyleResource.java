package br.com.ciclic.brewery.beer.application;

import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.domain.service.BeerStyleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BeerStyleResource {

    @Autowired
    private BeerStyleService service;

    @PostMapping(value = "/beerstyles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(@Valid @RequestBody BeerStyleTransferObject to) {
        log.info("Start process add new beer style.");
        String id = service.add(to);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        log.info("Beer style sucess!");
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/beerstyles/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> edit(@PathVariable("id") UUID id, @Valid @RequestBody BeerStyleTransferObject to) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/beerstyles/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/beerstyles/{id}")
    public ResponseEntity<BeerStyleTransferObject> find(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/beerstyles")
    public ResponseEntity<BeerStyleTransferObject> findAll(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(null);
    }
}
