package com.domain.springbootproject.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.domain.springbootproject.model.Categoria;
import com.domain.springbootproject.model.WhateverObject;

// Também poderia ser chamada de MovieRepository
// Inclusive, so é possível extender de JpaRepository se for por meio de uma interface
public interface MovieDAO extends JpaRepository<WhateverObject, Long> {
  /* 
  * Este metodo utiliza apenas conceitos do "Spring Data Repositories" que é entitulada de "Derived Queries (Consultas Derivadas)" 
  * Foi contruido utilziando conceitos de "Repository Query keywords" e "Query Methods Subject Keywords"
  * Utilizando estes padrões podemos fazer pesquisas ao banco de dados sem escrever queries em SQL
  * Utilizar este tipo de abordagem nos FLEXIBILIDADE e PORTABILIDADE pois, por exemplo, podemos mudar de banco de dados sem precisar fazer alterações em nossas consultas
  * findBy => "achar por"
  * WhateverField => Campo do objeto 
  * Containing => Contêm o trecho buscado 
  * IgnoreCase => Ignora maiúsculas e minúsculas 
  */ 
  Optional<ArrayList<WhateverObject>> findByWhateverFieldContainingIgnoreCase(String name);
  
  ArrayList<WhateverObject> findByCategoria(Categoria categoria);
  
  // Este tipo de abordagem é ruim pois diminui a PORTABILIDADE da aplicação, um exemplo seria a mudança de banco de dados, por questões de sintaticas de linguagem, alterações no código poderiam ser necessárias.
  // O valor padrão para "nativeQuery" é "false"
  @Query(value = "SELECT * FROM movies WHERE name = 'movieName'", nativeQuery = true)
  List<WhateverObject> findByName();

  // Caso quisermos trabalhar com SQL, é interessante utilzarmos a JPQL para mantermos a PORTABILIDADE e a FLEXIBILIDADE
  // DICA: 
  // JPQL = usa nomes de entidade (classes)
  // SQL nativo = usa nomes de tabela (banco de dados)
  @Query("SELECT m FROM WhateverObject m WHERE m.whateverField = :name")
  List<WhateverObject> findByName(@Param("name") String name);
}
