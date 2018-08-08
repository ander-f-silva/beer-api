package br.com.ciclic.brewery.beer.application.transferobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeerStyleTransferObject implements Serializable {
  private static final long serialVersionUID = -7849109492690934847L;

  @NotNull(message = "Field name is required")
  @NotBlank(message = "Field name is required")
  private String name;

  @Valid
  private TemperatureTransferObject temperature;
}
