package com.domain.springbootproject.model;

public enum Categoria {
  ACAO("Action"),
  ROMANCE("Romance"),
  COMEDIA("Comedy"),
  DRAMA("Drama"),
  CRIME("Crime");
  
  private String categoria;

  Categoria(String categoria) {
    this.categoria = categoria;
  }

  public static Categoria fromString(String text) {
    for (Categoria categoria : Categoria.values()) {
      if (categoria.categoria.equalsIgnoreCase(text)) {
        return categoria;
      }
    }
    throw new IllegalArgumentException("Nenhuma categoria encontrada");
  }
}