package com.ibsenc.myclosetapi.model;

import java.time.LocalDate;
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
  private String id;

  @NotBlank(message = "Name is required.")
  private String name;

  @NotNull(message = "Description cannot be null.")
  private String description;

  @Size(message = "Articles can have at most 4 images.", max = 4)
  @ElementCollection
  @NotNull(message = "ImageFileNames field cannot be null.")
  private List<String> imageFileNames;

  @NotNull(message = "Category cannot be null.")
  private String category;

  private String color;
  private String brand;
  private String size;

  @Size(message = "Articles can have at most 4 seasons.", max = 4)
  @ElementCollection
  private List<String> seasons;

  @ElementCollection
  private List<LocalDate> datesWorn;
}
