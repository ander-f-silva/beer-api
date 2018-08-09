package br.com.ciclic.brewery.beer.domain.adapter;

import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.TemperatureTransferObject;
import br.com.ciclic.brewery.beer.domain.entity.BeerStyle;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeerStyleAdapterTest {

    @Test
    public void shouldConvertEntity() {
        BeerStyleTransferObject to = new BeerStyleTransferObject("BeerStyle", new TemperatureTransferObject(1,-1));

        BeerStyleAdapter adapter = new BeerStyleAdapter(to);
        BeerStyle entity = adapter.converterEntity();

        assertEquals("BeerStyle", entity.getName());
        assertEquals(Integer.valueOf(1), entity.getTemperature().getMaximum());
        assertEquals(Integer.valueOf(-1), entity.getTemperature().getMinimum());
    }

}