package com.ibsenc.myclosetapi.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidFileTypeResponse {
  private String error;
}