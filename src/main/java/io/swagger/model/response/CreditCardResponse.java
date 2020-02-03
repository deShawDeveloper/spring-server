package io.swagger.model.response;

import java.util.Objects;
import io.swagger.model.CreditCard;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CreditCardResponse
 */
@Validated
@JsonInclude(Include.NON_NULL)
public class CreditCardResponse extends ArrayList<CreditCard>  {
	
	private boolean isSuccess = true;

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return true;
  }
  
  public CreditCardResponse() {
	  
  }
  
public CreditCardResponse(boolean isSuccess) {
	  this.isSuccess = false;
  }
  

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreditCardResponse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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

public boolean isSuccess() {
	return isSuccess;
}

public void setSuccess(boolean isSuccess) {
	this.isSuccess = isSuccess;
}
  
  
}

