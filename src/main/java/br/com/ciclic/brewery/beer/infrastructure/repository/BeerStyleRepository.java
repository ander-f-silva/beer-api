package br.com.ciclic.brewery.beer.infrastructure.repository;

import br.com.ciclic.brewery.beer.infrastructure.entity.BeerStyle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerStyleRepository extends MongoRepository<BeerStyle, String> {
}
