package br.com.ciclic.brewery.beer.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@Document
@AllArgsConstructor
public class BeerStyle implements Serializable {
    private static final long serialVersionUID = -2079189060173240104L;

    @Id
    private String id;

    private String name;

    private Integer maximum;

    private Integer minimum;

    public Integer calculateTemperatureDifference(Integer search) {
        Integer average = Math.abs(maximum + minimum) / 2;
        return Math.abs(search) - Math.abs(average);
    }
}
