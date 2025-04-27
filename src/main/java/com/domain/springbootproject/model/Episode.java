package com.domain.springbootproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "episodes")
public class Episode {
  public Episode() {}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // // @ManyToOne => Muitos "Episode" podem se relacionar com apenas um "Movie"
  @ManyToOne(optional = false)
  @JoinColumn(name = "movie_id", nullable = false)
  private WhateverObject movie;
  
  private int number;

  @Column(length = 3000)
  private String description;
  
  public Long getId() {
    return id;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public WhateverObject getMovie() {
    return movie;
  }

  public void setMovie(WhateverObject movie) {
    this.movie = movie;
  }
}
