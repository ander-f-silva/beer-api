package br.com.ciclic.brewery.beer.application.transferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BreweryTransferObject implements Serializable {
  private static final long serialVersionUID = -8630867156423286988L;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("list")
  private List<BeerStyleTransferObject> list;

}
