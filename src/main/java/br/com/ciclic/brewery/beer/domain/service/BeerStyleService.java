package br.com.ciclic.brewery.beer.domain.service;

import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.BreweryTransferObject;
import br.com.ciclic.brewery.beer.domain.adapter.BeerStyleAdapter;
import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;
import br.com.ciclic.brewery.beer.domain.exception.NotFoundException;
import br.com.ciclic.brewery.beer.infrastructure.repository.BeerStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BeerStyleService {

    @Autowired
    private BeerStyleRepository repository;

    @Transactional
    public Long add(BeerStyleTransferObject to) {
        BeerStyleAdapter adapter = new BeerStyleAdapter(to);
        BeerStyle beerStyle = adapter.converterEntity();
        beerStyle = repository.save(beerStyle);

        return beerStyle.getId();
    }

    @Transactional
    public void edit(Long id, BeerStyleTransferObject to) throws Exception {
        if (!repository.existsById(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        BeerStyleAdapter adapter = new BeerStyleAdapter(to);
        BeerStyle beerStyle = adapter.converterEntity();
        beerStyle.setId(id);
        repository.save(beerStyle);
    }

    @Transactional
    public void delete(Long id) throws Exception {
        if (!repository.existsById(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public BeerStyleTransferObject find(Long id) throws Exception {
        if (!repository.existsById(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        Optional<BeerStyle> optional= repository.findById(id);
        BeerStyle entity = optional.get();
        return new BeerStyleAdapter(entity).converterTransferObject();
    }

    @Transactional(readOnly = true)
    public BreweryTransferObject findAll() throws Exception {
        List<BeerStyle> entities = repository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("The beer style not found.");
        }

        List<BeerStyleTransferObject> list = entities.stream()
                                                     .map( entity -> new BeerStyleAdapter(entity).converterTransferObject())
                                                     .collect(Collectors.toList());

        return new BreweryTransferObject(list);
    }

    @Transactional(readOnly = true)
    public BeerStyleTransferObject findByTemperature(Integer temperature) throws Exception {
        BeerStyleAdapter adapter = new BeerStyleAdapter(repository.findByAverage(temperature).get(0));
        return  adapter.converterTransferObject();
    }
}