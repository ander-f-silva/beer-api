package br.com.ciclic.brewery.beer.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class Temperature implements Serializable {
    private static final long serialVersionUID = 8093391528631327037L;

    @Column(name = "MAXIMUM")
    private Integer maximum;

    @Column(name = "MINIMUM")
    private Integer minimum;

    @Column(name = "AVERAGE")
    private Integer average;

    public Temperature() {};

    public Temperature(Integer maximum, Integer minimun) {
        this.maximum = maximum;
        this.minimum = minimun;
        this.average = (maximum + minimum) / 2;
    }

}
