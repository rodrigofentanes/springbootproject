package com.domain.springbootproject.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecordExemple(
  @JsonAlias("page") Integer pagina,
  @JsonAlias("total_results") Integer resultadosEncontrados,
  @JsonAlias("total_pages") Integer totalDePaginas
) { }