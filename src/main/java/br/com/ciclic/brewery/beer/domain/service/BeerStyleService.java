package br.com.ciclic.brewery.beer.domain.service;

import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.domain.adapter.BeerStyleAdapter;
import br.com.ciclic.brewery.beer.infrastructure.entity.BeerStyleEntity;
import br.com.ciclic.brewery.beer.infrastructure.repository.BeerStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
