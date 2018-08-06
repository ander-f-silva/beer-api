package br.com.ciclic.brewery.beer.business.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BeerStyleVO implements Serializable {

  private static final long serialVersionUID = -7849109492690934847L;

  @NotNull(message = "Field name is required")
  @NotEmpty(message = "Field name is required")
  private String name;

  @Valid
  private TemperatureVO temperature;

}
