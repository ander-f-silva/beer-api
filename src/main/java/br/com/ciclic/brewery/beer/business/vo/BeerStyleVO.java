package br.com.ciclic.brewery.beer.business.vo;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
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
