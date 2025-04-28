package com.domain.springbootproject.model;

public enum Categoria {
  ACAO("Action", "Ação"),
  ROMANCE("Romance", "Romance"),
  COMEDIA("Comedy", "Comédia"),
  DRAMA("Drama", "Drama"),
  CRIME("Crime", "Crime");
  
  private String categoria;
  private String categoriaPtBr;

  /*
   * Note que este construtor recebendo dois itens
   * => Categoria(String categoria, String categoriaPtBr)
   * tem relação direta com por exemplo:
   * => ACAO("Action", "Ação")
   */
  

  Categoria(String categoria, String categoriaPtBr) {
    this.categoria = categoria;
    this.categoriaPtBr = categoriaPtBr;
  }

  public static Categoria fromString(String text) {
    for (Categoria categoria : Categoria.values()) {
      if (categoria.categoria.equalsIgnoreCase(text)) {
        return categoria;
      }
    }
    throw new IllegalArgumentException("Nenhuma categoria encontrada");
  }

  public static Categoria fromStringPtBr(String text) {
    for (Categoria categoria : Categoria.values()) {
      if (categoria.categoriaPtBr.equalsIgnoreCase(text)) {
        return categoria;
      }
    }
    throw new IllegalArgumentException("Nenhuma categoria encontrada");
  }
}