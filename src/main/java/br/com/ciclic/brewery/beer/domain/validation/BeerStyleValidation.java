package br.com.ciclic.brewery.beer.domain.validation;

import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;
import br.com.ciclic.brewery.beer.domain.exception.EntityExistsException;
import br.com.ciclic.brewery.beer.domain.exception.NotFoundException;
import br.com.ciclic.brewery.beer.infrastructure.repository.BeerStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeerStyleValidation {

    @Autowired
    private BeerStyleRepository repository;

    public void validateRepeatedName(String name) throws Exception{
        BeerStyle entity = repository.findByName(name);
        if (entity != null) {
            throw new EntityExistsException("Beer style has already been registered.");
        }
    }

    public void validateRepeatedName(String id, String name) throws Exception{
        BeerStyle entity = repository.findByName(name);
        if (entity != null && !entity.getId().equals(id)) {
            throw new EntityExistsException("Beer style has already been registered.");
        }
    }

    public void validateBeerStyleExist(String id) throws Exception {
        if (!repository.exists(id)) {
            throw new NotFoundException("The beer style not found.");
        }
    }

    public void validateBeerStyleExist(List<BeerStyle> entities) throws Exception {
        if (entities.isEmpty()) {
            throw new NotFoundException("The beer style not found.");
        }
    }

}
