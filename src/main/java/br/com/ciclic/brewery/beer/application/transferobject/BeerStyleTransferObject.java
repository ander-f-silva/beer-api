package br.com.ciclic.brewery.beer.application.transferobject;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BeerStyleTransferObject implements Serializable {
  private static final long serialVersionUID = -7849109492690934847L;

  @NotNull(message = "Field name is required")
  @NotBlank(message = "Field name is required")
  private String name;

  @Valid
  private TemperatureTransferObject temperature;

  public BeerStyleTransferObject() {
  }

  public BeerStyleTransferObject(String name, TemperatureTransferObject temperature) {
    this.name = name;
    this.temperature = temperature;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TemperatureTransferObject getTemperature() {
    return temperature;
  }

  public void setTemperature(TemperatureTransferObject temperature) {
    this.temperature = temperature;
  }
}
