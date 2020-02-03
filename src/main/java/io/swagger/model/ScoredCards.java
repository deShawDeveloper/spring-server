package io.swagger.model;

import java.beans.Transient;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Validated

@JsonInclude(Include.NON_NULL)
public class ScoredCards extends CreditCard {

	@JsonProperty("provider")
	@JsonInclude(Include.NON_NULL)
	private String provider = "ScoredCards";

	@JsonProperty("approvalRating")
	@JsonInclude(Include.NON_NULL)
	private Double approvalRating;
	
	@JsonIgnore
	@Transient
	public Double getApprovalRating() {
		return approvalRating;
	}

	public String getProvider() {
		return provider;
	}
	
	@Transient
	public void setApprovalRating(Double approvalRating) {
		this.approvalRating = approvalRating;
	}
	
	@NotNull
	public Double getCardScore() {
		return approvalRating * Math.pow((1 / apr), 2);
	}
}
