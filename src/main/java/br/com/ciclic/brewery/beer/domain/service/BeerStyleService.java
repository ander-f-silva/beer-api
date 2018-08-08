package br.com.ciclic.brewery.beer.domain.service;

import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.StylesTransferObject;
import br.com.ciclic.brewery.beer.domain.NotFoundException;
import br.com.ciclic.brewery.beer.domain.adapter.BeerStyleAdapter;
import br.com.ciclic.brewery.beer.infrastructure.entity.BeerStyleEntity;
import br.com.ciclic.brewery.beer.infrastructure.repository.BeerStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BeerStyleService {

    @Autowired
    private BeerStyleRepository repository;

    public String add(BeerStyleTransferObject to) {
        BeerStyleAdapter adapter = new BeerStyleAdapter(to);
        BeerStyleEntity beerStyleEntity = adapter.converterEntity();
        beerStyleEntity = repository.insert(beerStyleEntity);

        return beerStyleEntity.getId();
    }

    public void edit(String id, BeerStyleTransferObject to) throws Exception {
        if (!repository.exists(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        BeerStyleAdapter adapter = new BeerStyleAdapter(to);
        BeerStyleEntity beerStyleEntity = adapter.converterEntity();
        beerStyleEntity.setId(id);
        repository.save(beerStyleEntity);
    }

    public void delete(String id) throws Exception {
        if (!repository.exists(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        repository.delete(id);
    }

    public BeerStyleTransferObject find(String id) throws Exception {
        if (!repository.exists(id)) {
            throw new NotFoundException("The beer style not found.");
        }

        BeerStyleEntity entity = repository.findOne(id);
        return new BeerStyleAdapter(entity).converterTransferObject();
    }

    public StylesTransferObject findAll() throws Exception {
        List<BeerStyleEntity> entities = repository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("The beer style not found.");
        }

        List<BeerStyleTransferObject> list = entities.stream()
                                                        .map( entity -> new BeerStyleAdapter(entity).converterTransferObject())
                                                        .collect(Collectors.toList());

        return new StylesTransferObject(list);
    }
}
