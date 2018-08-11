package br.com.ciclic.brewery.beer.domain.decorator;

import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;
import lombok.Data;

@Data
public class BeerStyleDecorator implements Comparable<BeerStyleDecorator>{

    private BeerStyle beerStyle;

    private Integer temperatureDifference;

    public BeerStyleDecorator(BeerStyle beerStyle, Integer temperatureDifference) {
        this.beerStyle = beerStyle;
        this.temperatureDifference = beerStyle.calculateTemperatureDifference(temperatureDifference);
    }

    @Override
    public int compareTo(BeerStyleDecorator bd) {
        return this.getBeerStyle().getName().compareTo(bd.getBeerStyle().getName());
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        final BeerStyleDecorator bd = (BeerStyleDecorator) obj;
        if (this == bd) {
            return true;
        } else {
            return (this.getBeerStyle().getName().equals(bd.getBeerStyle().getName()));
        }
    }
    @Override
    public int hashCode() {
        int hashno = 7;
        hashno = 13 * hashno + (this.getBeerStyle().getName() == null ? 0 : this.getBeerStyle().getName().hashCode());
        return hashno;
    }
}
