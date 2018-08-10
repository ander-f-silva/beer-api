package br.com.ciclic.brewery.beer.domain.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BeerStyleTest {

    @Test
    public void shouldCalculateTemperatureAverage() {
        BeerStyle entity = new BeerStyle("Style", 2, -8);
        assertEquals(Integer.valueOf(-3), entity.getAverage());
    }

    @Test
    public void shouldCalculateTemperatureAnyLessDifferenceAndAverageAnyLess() {
        BeerStyle entity = new BeerStyle("Style", 2, -8);
        Integer difference = entity.calculateTemperatureDifference(-2);

        assertEquals(Integer.valueOf(1), difference);
    }

    @Test
    public void shouldCalculateTemperatureAnyLessDifferenceAndAveragePlus() {
        BeerStyle entity = new BeerStyle("Style", 3, -1);
        Integer difference = entity.calculateTemperatureDifference(-2);

        assertEquals(Integer.valueOf(3), difference);
    }

    @Test
    public void shouldCalculateTemperaturePlusDifferenceAndAverageAnyLess() {
        BeerStyle entity = new BeerStyle("Style", 2, -8);
        Integer difference = entity.calculateTemperatureDifference(2);

        assertEquals(Integer.valueOf(5), difference);
    }
}