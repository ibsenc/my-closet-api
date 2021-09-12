package com.ibsenc.myclosetapi.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResourceNotFoundResponse {

  private String error;
}
