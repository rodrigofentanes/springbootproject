package com.domain.springbootproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Movie")
@IdClass(WhateverObject.class)
public class WhateverObject {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true)
  private Long tmdbId;

  @Column(name = "name")
  private String whateverField;

  @Enumerated(EnumType.STRING)
  private Categoria categoria;

  @Transient
  private String campoNaoPersistidoNoBancoDeDados;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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

  public Long getTmdbId() {
    return tmdbId;
  }

  public void setTmdbId(Long tmdbId) {
    this.tmdbId = tmdbId;
  }
}
