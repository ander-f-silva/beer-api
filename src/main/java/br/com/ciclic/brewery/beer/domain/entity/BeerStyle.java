package br.com.ciclic.brewery.beer.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
public class BeerStyle implements Serializable {
    private static final long serialVersionUID = -2079189060173240104L;

    @Id
    private String id;
    private String name;
    private Integer maximum;
    private Integer minimum;
    private Integer average;

    public BeerStyle(String name, Integer maximum, Integer minimum) {
        this.name = name;
        this.maximum = maximum;
        this.minimum = minimum;
        this.average = (maximum + minimum) / 2;
    }

    public BeerStyle() {
    }

    public Integer calculateTemperatureDifference(Integer temperature) {
        if (temperature < 0)
            return Math.abs(average + Math.abs(temperature));
        else
            return Math.abs(average) + temperature;
    }
}
