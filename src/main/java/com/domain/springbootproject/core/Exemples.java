package com.domain.springbootproject.core;

import java.io.FileWriter;
import java.io.IOException;
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

  public void exemple1() throws JsonMappingException, JsonProcessingException, IOException, InterruptedException {
    Scanner scan = new Scanner(System.in);
    System.out.println("Digite o nome do filme que deseja pesquisar:");
    String name = scan.next();
    scan.close();
    
    ResponseSearchMovieDTO responseSearchMovieTO = ResponseSearchMovieDTO.getInstance().parse(
      TmdbServices.getInstance().searchMovieByName(name)
    );
  
    String jsonResult = new GsonBuilder().setPrettyPrinting().create().toJson(responseSearchMovieTO);
  
    FileWriter file = new FileWriter("Result.json");
    file.write(jsonResult);
    file.close();
  
    for (ResponseSearchMovieDTO.Result result : responseSearchMovieTO.getResults()) {
      System.out.println("#####################################");
      System.out.println(result.getTitle());
      System.out.println(result.getRelease_date());
      System.out.println(result.isAdult());
    }
  }

  public void exemple2() throws IOException, InterruptedException {
    Scanner scan = new Scanner(System.in);
    System.out.println("Digite o nome do filme que deseja pesquisar:");
    String name = scan.next();
    scan.close();
    
    var json = TmdbServices.getInstance().searchMovieByName(name);
    System.out.println(json);
    ConverteDados conversor = new ConverteDados();
    RecordExemple dados = conversor.obterDados(json, RecordExemple.class);
    System.out.println(dados);
  }
}
