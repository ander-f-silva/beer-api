package br.com.ciclic.brewery.beer.application;

import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.BreweryTransferObject;
import br.com.ciclic.brewery.beer.domain.service.BeerStyleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping(value = "/brewery/api/v1/beerstyles", produces = MediaType.APPLICATION_JSON_VALUE)
public class BeerStyleResource {

    @Autowired
    private BeerStyleService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> add(@Valid @RequestBody BeerStyleTransferObject to) {
        log.info("Start process add new beer style.");
        String id = service.add(to);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        log.info("Beer style add with success!");

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> edit(@PathVariable("id") String id, @Valid @RequestBody BeerStyleTransferObject to) throws Exception {
        log.info("Start process edit beer style " + id + ".");
        service.edit(id, to);
        log.info("Beer style edit with success!");

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) throws Exception {
        log.info("Start process delete beer style " + id + ".");
        service.delete(id);
        log.info("Beer style delete with success!");
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BeerStyleTransferObject> find(@PathVariable("id") String id) throws Exception {
        log.info("Start process find beer style " + id + ".");
        BeerStyleTransferObject to = service.find(id);
        log.info("Beer style find with success!");

        return ResponseEntity.ok(to);
    }

    @GetMapping
    public ResponseEntity<BreweryTransferObject> findAll() throws Exception {
        log.info("Start process find All beer style. ");
        BreweryTransferObject to = service.findAll();
        log.info("Beer style find All with success!");

        return ResponseEntity.ok(to);
    }

    @GetMapping(value = "/temperature/{temperature}")
    public ResponseEntity<BeerStyleTransferObject> findByTemperature(@PathParam(value = "temperature") Integer temperature) throws Exception {
        log.info("Start process find beer style with parameter temperature " + temperature + ".");
        BeerStyleTransferObject to = service.findByTemperature(temperature);
        log.info("Beer style find with success!");

        return ResponseEntity.ok(to);
    }
}
