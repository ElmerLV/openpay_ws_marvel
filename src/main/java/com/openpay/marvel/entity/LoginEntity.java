package com.openpay.marvel.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "login")
@Table(name = "login")
@Data
public class LoginEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String pass;

  private String keyPrivate;

  private String keyPublic;

}
