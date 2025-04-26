package com.domain.springbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.springbootproject.model.WhateverObject;

// Também poderia ser chamada de MovieRepository
// Inclusive, so é possível extender de JpaRepository se for por meio de uma interface
public interface MovieDAO extends JpaRepository<WhateverObject, Long> {

  
}
