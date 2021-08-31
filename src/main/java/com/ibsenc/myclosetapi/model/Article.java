package com.ibsenc.myclosetapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "articles")
public class Article {


  @Id
  String id;
  String name;
  String description;
}
