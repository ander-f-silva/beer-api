package br.com.ciclic.brewery.beer.application.rest;

import br.com.ciclic.brewery.beer.business.vo.BeerStyleVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/brewery")
public class BeerStyleController {

    @PostMapping(value = "/beerstyles", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
                                      , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@Valid @RequestBody BeerStyleVO beerStyle) {
        return Mono.empty();
    }

    @DeleteMapping(value = "/beerstyles/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable("id") UUID id) {
        return Mono.empty();
    }

    @GetMapping(value = "/beerstyles/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<BeerStyleVO> find(@PathVariable("id")  UUID id) {
        return Mono.just(new BeerStyleVO());
    }

    @PutMapping(value = "/beerstyles/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
                                          , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> update(@PathVariable("id") UUID id, @Valid @RequestBody BeerStyleVO beerStyle) {
        return Mono.empty();
    }

}
