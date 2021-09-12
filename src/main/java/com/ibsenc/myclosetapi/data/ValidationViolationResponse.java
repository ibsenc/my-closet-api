package com.ibsenc.myclosetapi.data;

import java.util.List;
import lombok.Data;

@Data
public class ValidationViolationResponse {

  private List<String> errors;
}
