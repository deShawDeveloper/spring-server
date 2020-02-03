package io.swagger.model.request;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreditCardRequest
 */
@Validated
@JsonInclude(content=Include.NON_NULL)
public class CreditCardRequest  implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@JsonProperty("name")
  private String name = null;

  @JsonProperty("creditScore")
  private Integer creditScore = null;
  
  @JsonProperty("score")
  private Integer score = null;

  @JsonProperty("salary")
  private Integer salary = null;

  public CreditCardRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Users full name
   * @return name
  **/
  @ApiModelProperty(example = "John Smith", required = true, value = "Users full name")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CreditCardRequest creditScore(Integer creditScore) {
    this.creditScore = creditScore;
    return this;
  }

  /**
   * Credit score between 0 and 700
   * minimum: 0
   * maximum: 700
   * @return creditScore
  **/
  @ApiModelProperty(value = "Credit score between 0 and 700")
  @NotNull

@Min(0) @Max(700) 
  public Integer getCreditScore() {
    return creditScore;
  }

  @ApiModelProperty(value = "Credit score between 0 and 700")
  @Min(0) @Max(700) 
  public Integer getScore() {
    return score;
  }

  public void setCreditScore(Integer creditScore) {
    this.creditScore = creditScore;
    this.score = creditScore;
  }

  public CreditCardRequest salary(Integer salary) {
    this.salary = salary;
    return this;
  }

  /**
   * Users annual salary
   * minimum: 0
   * @return salary
  **/
  @ApiModelProperty(required = true, value = "Users annual salary")
  @NotNull

@Min(0)
  public Integer getSalary() {
    return salary;
  }

  public void setSalary(Integer salary) {
    this.salary = salary;
  }


  @Override
  public int hashCode() {
    return Objects.hash(name, creditScore, salary,score);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreditCardRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    creditScore: ").append(toIndentedString(creditScore)).append("\n");
    sb.append("    salary: ").append(toIndentedString(salary)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

