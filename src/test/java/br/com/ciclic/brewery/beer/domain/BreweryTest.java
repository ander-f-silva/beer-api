package br.com.ciclic.brewery.beer.domain;

import br.com.ciclic.brewery.beer.infrastructure.entity.BeerStyle;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BreweryTest {

    @Test
    public void shouldSearchApproximateAverageTemperature() {
        Integer valueSearch = -2;
        BeerStyle dunkel = BeerStyle.builder().name("Dunkel").maximum(2).minimum(-8).build();
        BeerStyle weissbier = BeerStyle.builder().name("Weissbier").maximum(2).minimum(-8).build();

        Brewery brewery = new Brewery(Arrays.asList(dunkel, weissbier));
        BeerStyle entity = brewery.searchApproximateBeerStyle(valueSearch);

        assertEquals("Dunkel", entity.getName());
    }

    public static void main(String[] args) {
        int search = -2;

        int min = -1;
        int max = 3;

        int average =  (max + min) / 2;

        System.out.println(average);


        System.out.println(Math.abs(search) - Math.abs(average));

    }

}