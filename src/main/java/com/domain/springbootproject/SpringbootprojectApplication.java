package com.domain.springbootproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.domain.springbootproject.core.Exemples;
import com.domain.springbootproject.repository.EpisodeRepository;
import com.domain.springbootproject.repository.MovieDAO;

@SpringBootApplication
public class SpringbootprojectApplication implements CommandLineRunner {

	// Injeção de dependência, este deve estar numa classe gerenciada pelo Spring, neste caso a SpringbootprojectApplication é uma classe gerenciada pelo Spring.
	// A anotação @Autowired é utilizada para fazer a injeção de dependência
	@Autowired
	private MovieDAO movieDAO;

	@Autowired
	private EpisodeRepository episodeRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Exemples.getInstance().exemple7();
		// new Exemples(movieDAO).getNewInstance(movieDAO).exemple5();
		new Exemples(movieDAO, episodeRepository).getNewInstance(movieDAO, episodeRepository).exemple10();
	}
}
