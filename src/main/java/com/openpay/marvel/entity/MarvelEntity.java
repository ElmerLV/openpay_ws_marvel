package com.openpay.marvel.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "marvel")
@Table(name = "marvel")
@Data
public class MarvelEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String basePath;

  private LocalDateTime horaConsulta;

  public MarvelEntity() {
    this.horaConsulta = LocalDateTime.now();
  }

}
