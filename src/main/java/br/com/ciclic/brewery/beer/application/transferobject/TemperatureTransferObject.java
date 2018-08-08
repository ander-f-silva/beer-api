package br.com.ciclic.brewery.beer.application.transferobject;

import br.com.ciclic.brewery.beer.application.validation.TemperatureMaximumLessMinimum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TemperatureMaximumLessMinimum
public class TemperatureTransferObject implements Serializable {
  private static final long serialVersionUID = 300754025090035561L;

  @NotNull(message = "Field maximum is required")
  @Max(100)
  @Min(-100)
  private Integer maximum;

  @NotNull(message = "Field minimum is required")
  @Max(100)
  @Min(-100)
  private Integer minimum;
}
