package com.domain.springbootproject.services;

public interface IConverteDados {
  <T> T obterDados(String json, Class<T> classe);
}
