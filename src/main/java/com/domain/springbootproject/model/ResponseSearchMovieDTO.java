package com.domain.springbootproject.model;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;

public class ResponseSearchMovieDTO {
  private static final ResponseSearchMovieDTO instance = new ResponseSearchMovieDTO();
	public static ResponseSearchMovieDTO getInstance() { return instance; }
  
  private int page;
  private ArrayList<Result> results;
  private int total_pages;
  private int total_results;

  public int getPage() {
    return page;
  }

  public ArrayList<Result> getResults() {
    return results;
  }

  public int getTotal_pages() {
    return total_pages;
  }

  public int getTotal_results() {
    return total_results;
  }

  public class Result{
    private boolean adult;
    private String backdrop_path;
    private ArrayList<Integer> genre_ids;
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;

    public boolean isAdult() {
      return adult;
    }
    public String getBackdrop_path() {
      return backdrop_path;
    }
    public ArrayList<Integer> getGenre_ids() {
      return genre_ids;
    }
    public int getId() {
      return id;
    }
    public String getOriginal_language() {
      return original_language;
    }
    public String getOriginal_title() {
      return original_title;
    }
    public String getOverview() {
      return overview;
    }
    public double getPopularity() {
      return popularity;
    }
    public String getPoster_path() {
      return poster_path;
    }
    public String getRelease_date() {
      return release_date;
    }
    public String getTitle() {
      return title;
    }
    public boolean isVideo() {
      return video;
    }
    public double getVote_average() {
      return vote_average;
    }
    public int getVote_count() {
      return vote_count;
    }

    @Override
    public String toString() {
      return "Titulo: " + getTitle() + (getRelease_date().isEmpty() ? "" : ", Data de lançamento: " + getRelease_date()) + ", Adulto: " + isAdult();
    }
  }

  public ResponseSearchMovieDTO parse(String json) throws JsonMappingException, JsonProcessingException {
    ResponseSearchMovieDTO data = new Gson().fromJson(json, ResponseSearchMovieDTO.class);
    return (ResponseSearchMovieDTO) data;
  }

  
}
