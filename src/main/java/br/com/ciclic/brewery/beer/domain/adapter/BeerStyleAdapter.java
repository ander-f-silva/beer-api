package br.com.ciclic.brewery.beer.domain.adapter;

import br.com.ciclic.brewery.beer.application.transferobject.BeerStyleTransferObject;
import br.com.ciclic.brewery.beer.application.transferobject.TemperatureTransferObject;
import br.com.ciclic.brewery.beer.infrastructure.entity.BeerStyleEntity;
import lombok.AllArgsConstructor;

public class BeerStyleAdapter {

    private BeerStyleTransferObject to;

    private BeerStyleEntity entity;

    public BeerStyleAdapter(BeerStyleTransferObject to) {
        this.to = to;
    }

    public BeerStyleAdapter(BeerStyleEntity entity) {
        this.entity = entity;
    }

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

    public BeerStyleTransferObject converterTransferObject() {
        String name = entity.getName();
        Integer maximum = entity.getMaximum();
        Integer minimum = entity.getMinimum();

        return new BeerStyleTransferObject(name, new TemperatureTransferObject(maximum, minimum));
    }
}
