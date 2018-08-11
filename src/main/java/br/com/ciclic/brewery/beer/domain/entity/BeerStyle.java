package br.com.ciclic.brewery.beer.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }
}
