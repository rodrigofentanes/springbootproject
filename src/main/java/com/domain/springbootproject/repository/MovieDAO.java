package com.domain.springbootproject.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.springbootproject.model.WhateverObject;

// Também poderia ser chamada de MovieRepository
// Inclusive, so é possível extender de JpaRepository se for por meio de uma interface
public interface MovieDAO extends JpaRepository<WhateverObject, Long> {
  // Este metodo utiliza apenas conceitos do "Spring Data Repositories"
  // Foi contruido utilziando conceitos de "Repository Query keywords" e "Query Methods Subject Keywords"
  // Utilizando estes padrões podemos fazer pesquisas ao banco de dados sem escrever queries em SQL
  Optional<ArrayList<WhateverObject>> findByWhateverFieldContainingIgnoreCase(String name);
  
}
