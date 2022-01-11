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
@Table(name = "outfits")
public class Outfit {

  @Id
  private String id;

  @NotBlank(message = "Name is required.")
  private String name;

  @NotNull(message = "Description cannot be null.")
  private String description;

  @Size(message = "Outfits can have at most 4 images.", max = 4)
  @ElementCollection
  @NotNull(message = "Image file names cannot be null.")
  private List<String> imageFileNames;

  @Size(message = "Outfits can have at most 20 articles.", max = 20)
  @ElementCollection
  @NotNull(message = "Article ids cannot be null.")
  private List<String> articleIds;

  private String occasion;
  private String style;

//  @Size(message = "Outfits can have at most 4 seasons.", max = 4)
//  @ElementCollection
//  private List<String> seasons;
}
