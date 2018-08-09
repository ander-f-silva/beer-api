package br.com.ciclic.brewery.beer.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TB_BEER_STYLE")
public class BeerStyle implements Serializable {
    private static final long serialVersionUID = -2079189060173240104L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Embedded
    private Temperature temperature;

    public BeerStyle() {};

    public BeerStyle(String name, Integer maximum, Integer minimun) {
        this.name = name;
        this.temperature = new Temperature(maximum, minimun);
    }

}
