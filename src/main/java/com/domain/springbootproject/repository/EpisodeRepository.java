package com.domain.springbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.springbootproject.model.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

}
