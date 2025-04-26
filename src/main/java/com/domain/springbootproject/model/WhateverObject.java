package com.domain.springbootproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Movie")
public class WhateverObject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String whateverField;

  @Enumerated(EnumType.STRING)
  private Categoria categoria;

  @Transient
  private String campoNaoPersistidoNoBancoDeDados;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getWhateverField() {
    return whateverField;
  }

  public void setWhateverField(String whateverField) {
    this.whateverField = whateverField;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  
}
