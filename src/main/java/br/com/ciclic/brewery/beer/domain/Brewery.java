package br.com.ciclic.brewery.beer.domain;

import br.com.ciclic.brewery.beer.infrastructure.entity.BeerStyle;

import java.util.List;
import java.util.stream.Collectors;

public class Brewery {

    private List<BeerStyle> beerStyles;

    public Brewery(List<BeerStyle> beerStyles) {
        this.beerStyles = beerStyles;
    }

    public BeerStyle searchApproximateBeerStyle(Integer value) {

        beerStyles = beerStyles.stream()
                               .sorted((b1, b2) -> b1.calculateTemperatureDifference(value).compareTo(b2.calculateTemperatureDifference(value)))
                               .collect(Collectors.toList());

        return beerStyles.stream().findFirst().get();
    }
}
