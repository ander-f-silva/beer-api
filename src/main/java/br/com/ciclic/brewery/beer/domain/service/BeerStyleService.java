package br.com.ciclic.brewery.beer.domain.service;

import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.BreweryTransferObject;
import br.com.ciclic.brewery.beer.domain.adapter.BeerStyleAdapter;
import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;
import br.com.ciclic.brewery.beer.domain.exception.NotFoundException;
import br.com.ciclic.brewery.beer.infrastructure.repository.BeerStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BeerStyleService {

    @Autowired
    private BeerStyleRepository repository;

    public Long add(BeerStyleTransferObject to) {
        BeerStyleAdapter adapter = new BeerStyleAdapter(to);
        BeerStyle beerStyle = adapter.converterEntity();
        beerStyle = repository.save(beerStyle);

        return beerStyle.getId();
    }

    public void edit(Long id, BeerStyleTransferObject to) throws Exception {
        if (!repository.exists(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        BeerStyleAdapter adapter = new BeerStyleAdapter(to);
        BeerStyle beerStyle = adapter.converterEntity();
        beerStyle.setId(id);
        repository.save(beerStyle);
    }

    public void delete(Long id) throws Exception {
        if (!repository.exists(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        repository.delete(id);
    }

    public BeerStyleTransferObject find(Long id) throws Exception {
        if (!repository.exists(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        BeerStyle entity = repository.findOne(id);
        return new BeerStyleAdapter(entity).converterTransferObject();
    }

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

    public BeerStyleTransferObject findByTemperature(Integer temperature) throws Exception {
        return null;
    }
}
