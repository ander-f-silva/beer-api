package br.com.ciclic.brewery.beer.infrastructure.repository;

import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeerStyleRepository extends JpaRepository<BeerStyle, Long> {


    @Query("select b from BeerStyle b WHERE b.temperature.average= :temperature")
    List<BeerStyle> findByAverage(@Param("temperature") Integer temperature);

}
