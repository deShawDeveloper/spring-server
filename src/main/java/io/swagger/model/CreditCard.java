package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import java.beans.Transient;
import java.text.DecimalFormat;

import javax.validation.constraints.*;

/**
 * CreditCard
 */
@Validated

@JsonInclude(Include.NON_NULL)
public abstract class CreditCard implements Comparable<CreditCard>{
	
	protected final DecimalFormat numberFormat = new DecimalFormat("#.000");
	
  private String name;
  
  @JsonProperty("name")
  @JsonInclude(Include.NON_NULL)
  private String cardName;

  @JsonProperty("apr")
  @JsonInclude(Include.NON_NULL)
  protected Double apr;
  
  @JsonProperty("card")
  @JsonInclude(Include.NON_NULL)
  private String card;
  

  public CreditCard name(String name) {
    this.name = name;
    return this;
  }
  
  protected abstract String getProvider();

  /**
   * Name of the credit card product
   * @return name
  **/
  @ApiModelProperty(required = true, value = "Name of the credit card product")
  @NotNull
  @Transient
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CreditCard apr(Double apr) {
    this.apr = apr;
    return this;
  }

  /**
   * Annual percentage rate for the card
   * @return apr
  **/
  @ApiModelProperty(required = true, value = "Annual percentage rate for the card")
  @NotNull


  public Double getApr() {
    return apr;
  }

  public void setApr(Double apr) {
    this.apr = apr;
  }
  
  protected abstract Double getCardScore();

  public String getCardName() {
	return cardName;
}

public void setCardName(String cardName) {
	this.cardName = cardName;
}

public String getCard() {
	return card;
}

public void setCard(String card) {
	this.card = card;
}

@Override
public int compareTo(CreditCard o) {
	return Double.compare(o.getCardScore(), this.getCardScore()) ;
}

@ApiModelProperty(required = true, value = "The score given to the credit card based on the scoring algorithm")
@NotNull
@JsonProperty("cardScore")
public double getFormattedCardScore() {
	return Double.parseDouble(numberFormat.format(this.getCardScore()));
}


}

