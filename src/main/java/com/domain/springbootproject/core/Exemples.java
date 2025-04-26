package com.domain.springbootproject.core;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.domain.springbootproject.model.Categoria;
import com.domain.springbootproject.model.RecordExemple;
import com.domain.springbootproject.model.ResponseSearchMovieDTO;
import com.domain.springbootproject.model.WhateverObject;
import com.domain.springbootproject.repository.MovieDAO;
import com.domain.springbootproject.services.ConverteDados;
import com.domain.springbootproject.services.TmdbServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.GsonBuilder;

public class Exemples {
  private MovieDAO movieDAO;
  private Scanner scan = new Scanner(System.in);
  
  public Exemples() {}
  private static final Exemples instance = new Exemples();
	public static Exemples getInstance() { return instance; }

  public Exemples(MovieDAO movieDAO) { this.movieDAO = movieDAO; }
	public Exemples getNewInstance(MovieDAO movieDAO) { return new Exemples(movieDAO); }

  public void exemple1() throws JsonMappingException, JsonProcessingException, IOException, InterruptedException {
    System.out.println("Digite o nome do filme que deseja pesquisar:");
    String name = scan.next();
    scan.close();
    
    HttpResponse<String> response = TmdbServices.getInstance().searchMovieByName(name);

    if (response.statusCode() == 200) {
      ResponseSearchMovieDTO responseSearchMovieTO = ResponseSearchMovieDTO.getInstance().parse(response.body());
    
      String jsonResult = new GsonBuilder().setPrettyPrinting().create().toJson(responseSearchMovieTO);
    
      FileWriter file = new FileWriter("Result.json");
      file.write(jsonResult);
      file.close();
    
      if (responseSearchMovieTO.getResults() != null && !responseSearchMovieTO.getResults().isEmpty()) {
        for (ResponseSearchMovieDTO.Result result : responseSearchMovieTO.getResults()) {
          System.out.println("#####################################");
          System.out.println(result.getTitle());
          System.out.println(result.getRelease_date());
          System.out.println(result.isAdult());
        }
      }
    }
  }

  public void exemple2() throws IOException, InterruptedException {
    System.out.println("Digite o nome do filme que deseja pesquisar:");
    String name = scan.nextLine();
    scan.close();

    HttpResponse<String> response = TmdbServices.getInstance().searchMovieByName(name);
    var json = response.body();
    System.out.println(json);
    ConverteDados conversor = new ConverteDados();
    RecordExemple dados = conversor.obterDados(json, RecordExemple.class);
    System.out.println(dados);
  }

  public void exemple3() throws IOException, InterruptedException {
    System.out.println("Digite o nome do filme que deseja pesquisar:");
    String name = scan.nextLine();
    scan.close();

    HttpResponse<String> response = TmdbServices.getInstance().searchMovieByName(name);

    if (response.statusCode() == 200) {
      ResponseSearchMovieDTO responseSearchMovieTO = ResponseSearchMovieDTO.getInstance().parse(response.body());
    
      String jsonResult = new GsonBuilder().setPrettyPrinting().create().toJson(responseSearchMovieTO);
    
      FileWriter file = new FileWriter("Result.json");
      file.write(jsonResult);
      file.close();
    
      
      if (responseSearchMovieTO.getResults() != null && !responseSearchMovieTO.getResults().isEmpty()) {
        // Exemplo de Stream com LAMBDA
        // O "peek" serve para debugar a execucao da stream
        responseSearchMovieTO.getResults().stream()
          .sorted(Comparator.comparing(r -> r.getTitle()))
          // .sorted(Comparator.comparing(ResponseSearchMovieDTO::getTitle ))
          .peek(e -> System.out.println("peek 01 => " + e))
          .limit(5)
          .peek(e -> System.out.println("peek 02 => " + e))
          .filter(r -> r.isAdult() == true)
          .peek(e -> System.out.println("peek 03 => " + e))
          .map(r -> r.getTitle().toUpperCase())
          .peek(e -> System.out.println("peek 04 => " + e))
          .forEach(System.out::println);

        System.out.println("###################################");

        // Exemplo de LAMBDA
        responseSearchMovieTO.getResults().forEach(r -> System.out.println(
          "Titulo: " + r.getTitle()
          + (r.getRelease_date().isEmpty() ? "" : ", Data de lançamento: " + r.getRelease_date()) 
          + ", Adulto: " + r.isAdult()
        ));

        /*
        *** Caso quisermos utilizar o flatmap() e adiciona-lo a uma lista mutavel, podemos fazer:
        * List<DataType> nomeDaLista = outraLista.stream().flatmap(t -> t.listaDentroDeOutraLista().stream()).collect(Collectors.toList());
        * List<DataType> nomeDaLista = outraLista.stream().map(t -> new Serie(t)).collect(Collectors.toList());
        *
        *** Caso quisermos utilizar o flatmap() e adiciona-lo a uma lista imutavel, podemos fazer:
        * List<DataType> nomeDaLista = outraLista.stream().flatmap(t -> t.listaDentroDeOutraLista().stream()).toList();
        * 
        *** collect e Collectors são muito uteis para agrupamento de valores, criar estatisticas, etc. Ex:
        * .collect(Collectors.groupingBy(Episodio::getTemporada), Collectors.averagingDouble(Episodio::getAvaliacao))
        */
      }
    }
  }

  public void exemple4() {
    System.out.println(Categoria.values());
    System.out.println("###################################");
    System.out.println(Categoria.valueOf("ACAO"));
    System.out.println("###################################");
    System.out.println(Categoria.COMEDIA);
    System.out.println("###################################");
    System.out.println(Categoria.fromString("Action"));
    System.out.println("###################################");
    for (Categoria categoria : Categoria.values()) {
      System.out.println(categoria);
    }
  }

  public void exemple5() throws JsonMappingException, JsonProcessingException, IOException, InterruptedException {
    System.out.println("Digite o nome do filme que deseja pesquisar:");
    String name = scan.next();
    scan.close();
    
    HttpResponse<String> response = TmdbServices.getInstance().searchMovieByName(name);

    if (response.statusCode() == 200) {
      ResponseSearchMovieDTO responseSearchMovieTO = ResponseSearchMovieDTO.getInstance().parse(response.body());
      List<WhateverObject> listMovies = new ArrayList<>();
      String jsonResult = new GsonBuilder().setPrettyPrinting().create().toJson(responseSearchMovieTO);
      FileWriter file = new FileWriter("Result.json");
      file.write(jsonResult);
      file.close();
    
      if (responseSearchMovieTO.getResults() != null && !responseSearchMovieTO.getResults().isEmpty()) {
        for (ResponseSearchMovieDTO.Result result : responseSearchMovieTO.getResults()) {
          Long tmdbId = (long) result.getId();
          System.out.println("tmdbId => " + tmdbId + " ##################################");
          WhateverObject movie = new WhateverObject();
          movie.setTmdbId(tmdbId);
          movie.setWhateverField(result.getTitle());
          movie.setCategoria(Categoria.fromString("Action"));
          listMovies.add(movie);
        }

        movieDAO.saveAll(listMovies);
      }

    }
  }
}
