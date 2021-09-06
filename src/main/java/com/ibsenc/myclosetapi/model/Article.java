package com.ibsenc.myclosetapi.model;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
  @Size(message = "Articles can have at most 4 images.", min = 0, max = 4)
  @ElementCollection
  @NotNull(message = "ImageFileNames field cannot be null.")
  private List<String> imageFileNames;
}
