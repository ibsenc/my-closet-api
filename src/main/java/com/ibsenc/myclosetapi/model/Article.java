package com.ibsenc.myclosetapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "articles")
public class Article {

  @Id
  String id;
  @NotBlank(message = "Name is required.")
  String name;
  @NotNull(message = "Description cannot be null.")
  String description;
}
