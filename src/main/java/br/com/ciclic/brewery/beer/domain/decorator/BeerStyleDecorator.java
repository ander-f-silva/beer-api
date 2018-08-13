package br.com.ciclic.brewery.beer.domain.decorator;

import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;

public class BeerStyleDecorator {

    private BeerStyle beerStyle;

    private Integer temperatureDifference;

    public BeerStyleDecorator(BeerStyle beerStyle, Integer temperatureDifference) {
        this.beerStyle = beerStyle;
        this.temperatureDifference = beerStyle.calculateTemperatureDifference(temperatureDifference);
    }

    public BeerStyle getBeerStyle() {
        return beerStyle;
    }

    public Integer getTemperatureDifference() {
        return temperatureDifference;
    }
}
