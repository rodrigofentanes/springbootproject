package com.domain.springbootproject.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

// @Entity  => Especifica que é uma entidade do banco de dados
// @Table => Especifica o nome da tabela do banco de dados 
// name = "movies" => Especifica que o nome da tabela no banco de dados. 
// Obs.: Nomes de tabelas em bancos de dados normalmente estarão em letras minusculas, com palavras separadas por underline e no plural
@Entity 
@Table(name = "movies") 
public class WhateverObject {
  public WhateverObject() {}

  // @Id => Especifica que este campo refere-se a um Id
  // @GeneratedValue => Especifica que é um valor gerado automaticamente pelo proprio banco de dados
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // nullable = false => Especifica que o campo não aceita valores nulos 
  // unique = true => Especifica que cada valor será único, ou seja, que não existirão dois valores iguais no banco de dados
  @Column(nullable = false, unique = true)
  private Long tmdbId;

  // name => Especifica o nome da coluna do banco de dados
  @Column(name = "name")
  private String whateverField;

  @Enumerated(EnumType.STRING)
  private Categoria categoria;

  // @Transient => Especifica que um campo não será persistido no banco de dados, ou seja, não será utilizado no banco de dados
  @Transient
  private String campoNaoPersistidoNoBancoDeDados;

  // @OneToMany => Um "Movie" pode se relacionar com muitos "Episode" 
  // mappedBy = "movie" => Também mapeamos o atributo "movie" para especificar o relacionamento, é o nome do atributo (Campo) na outra classe
  // cascade = CascadeType.ALL => Permite que dois objetos relacionados possam ser salvos ao mesmo tempo
  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
  private List<Episode> episodes;

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

  public List<Episode> getEpisodes() {
    return episodes;
  }

  public void setEpisodes(List<Episode> episodes) {
    this.episodes = episodes;
  }

  public void addEpisode(Episode episode) {
    this.episodes.add(episode);
    episode.setMovie(this);
  }
}
