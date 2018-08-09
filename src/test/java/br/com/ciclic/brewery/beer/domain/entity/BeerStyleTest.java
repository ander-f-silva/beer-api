package br.com.ciclic.brewery.beer.domain.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BeerStyleTest {

    @Test
    public void shouldCalculateTemperatureDifference() {
        BeerStyle entity = new BeerStyle("Dunkel", 2, -8);
        assertEquals(Integer.valueOf(-3), entity.getTemperature().getAverage());
    }
}