package com.domain.springbootproject.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TmdbServices {
  private static final TmdbServices instance = new TmdbServices();
	public static TmdbServices getInstance() { return instance; }

  private final String BASE_URL = "https://api.themoviedb.org";
  private final String API_KEY = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhNGViNjk5N2E4YjM3NjhhZDc5M2ZkZGNmOTYxNTE0NCIsIm5iZiI6MTYwNTEzNjMzNi4xODksInN1YiI6IjVmYWM2ZmQwZmNiOGNjMDA0MGY4NzczNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.j_J0q5nvtJMlt5wB6prMvYY305nFyPjBkSIlNskp3X8";

  public HttpResponse<String> searchMovieByName(String name) throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create(BASE_URL + "/3/search/movie?query=" + name.replace(" ", "+") + "&include_adult=true&language=en-US"))
    .setHeader("accept", "application/json")
    .setHeader("Authorization", "Bearer " + API_KEY)
    .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return response;
  }
}
