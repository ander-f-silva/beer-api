package br.com.ciclic.brewery.beer.domain.adapter;

import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.infrastructure.entity.BeerStyleEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BeerStyleAdapter {

    private BeerStyleTransferObject to;

    public BeerStyleEntity converterEntity() {
        String name = to.getName();
        Integer maximum = to.getTemperature().getMaximum();
        Integer minimum = to.getTemperature().getMinimum();

        return BeerStyleEntity.builder()
                .name(name)
                .maximum(maximum)
                .minimum(minimum)
                .build();
    }
}
