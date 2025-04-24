package com.domain.springbootproject.core;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.Scanner;

import com.domain.springbootproject.model.RecordExemple;
import com.domain.springbootproject.model.ResponseSearchMovieDTO;
import com.domain.springbootproject.services.ConverteDados;
import com.domain.springbootproject.services.TmdbServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.GsonBuilder;

public class Exemples {
  private static final Exemples instance = new Exemples();
	public static Exemples getInstance() { return instance; }

  private Scanner scan = new Scanner(System.in);

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
        * Caso quisermos utilizar o flatmap() e adiciona-lo a uma lista mutavel, podemos fazer:
        * List<DataType> nomeDaLista = outraLista.stream().flatmap(t -> t.listaDentroDeOutraLista().stream()).collect(Collectors.toList());
        *
        * Caso quisermos utilizar o flatmap() e adiciona-lo a uma lista imutavel, podemos fazer:
        * List<DataType> nomeDaLista = outraLista.stream().flatmap(t -> t.listaDentroDeOutraLista().stream()).toList();
        * 
        * collect e Collectors muito util para agrupamento de valores, criar estatisticas, etc. Ex:
        * .collect(Collectors.groupingBy(Episodio::getTemporada), Collectors.averagingDouble(Episodio::getAvaliacao))
        */
      }
    }
  }
}
