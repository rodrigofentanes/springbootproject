package com.domain.springbootproject;

import java.io.FileWriter;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.domain.springbootproject.model.ResponseSearchMovieDTO;
import com.domain.springbootproject.services.TmdbServices;
import com.google.gson.GsonBuilder;

@SpringBootApplication
public class SpringbootprojectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o nome do filme que deseja pesquisar:");
		String name = scan.next();
		scan.close();
		
		ResponseSearchMovieDTO responseSearchMovieTO = new ResponseSearchMovieDTO().parse(
			TmdbServices.getInstance().serachMovieByName(name)
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

}
