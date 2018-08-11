package br.com.ciclic.brewery.beer.application.transferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class BreweryTransferObject implements Serializable {
  private static final long serialVersionUID = -8630867156423286988L;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  @JsonProperty("list")
  private List<BeerStyleTransferObject> list;

  public BreweryTransferObject(List<BeerStyleTransferObject> list) {
    this.list = list;
  }

  public BreweryTransferObject() {
  }

  public List<BeerStyleTransferObject> getList() {
    return list;
  }

  public void setList(List<BeerStyleTransferObject> list) {
    this.list = list;
  }
}
