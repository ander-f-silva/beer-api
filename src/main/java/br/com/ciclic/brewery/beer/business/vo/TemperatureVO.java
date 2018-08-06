package br.com.ciclic.brewery.beer.business.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class TemperatureVO implements Serializable {

    private static final long serialVersionUID = 300754025090035561L;

    @NotNull
    @Size(min = -100, max = 100)
    private Integer maximum;

    @NotNull
    @Size(min = -100, max = 100)
    private Integer minimum;

}
