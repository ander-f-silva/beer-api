package br.com.ciclic.brewery.beer.infrastructure.repository;

import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerStyleRepository extends JpaRepository<BeerStyle, Long> {
}
