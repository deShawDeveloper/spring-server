package io.swagger.model;

import java.beans.Transient;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@Validated

@JsonInclude(Include.NON_NULL)
public class CsCards extends CreditCard {

	@JsonProperty("provider")
	@JsonInclude(Include.NON_NULL)
	private String provider = "CSCards";

	@JsonProperty("eligibility")
	@JsonInclude(Include.NON_NULL)
	private Double eligibility;
	
	public String getProvider() {
		return provider;
	}

	@Transient
	public Double getCardScore() {
		return eligibility * Math.pow((1 / apr), 2);
	}
	
	
	
}
