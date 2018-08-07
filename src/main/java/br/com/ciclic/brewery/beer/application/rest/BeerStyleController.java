package br.com.ciclic.brewery.beer.application.rest;

import br.com.ciclic.brewery.beer.business.vo.BeerStyleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/brewery/api/v1", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
public class BeerStyleController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/beerstyles", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Void> create(@Valid @RequestBody BeerStyleVO beerStyle) {
        return Mono.empty();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/beerstyles/{id}")
    public Mono<Void> delete(@PathVariable("id") UUID id) {
        return Mono.empty();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/beerstyles/{id}")
    public Mono<BeerStyleVO> find(@PathVariable("id")  UUID id) {
        return Mono.just(new BeerStyleVO());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/beerstyles/{id}", consumes = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<Void> update(@PathVariable("id") UUID id, @Valid @RequestBody BeerStyleVO beerStyle) {
        return Mono.empty();
    }

}
