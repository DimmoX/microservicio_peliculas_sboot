package com.example.peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.peliculas.model.PeliculasModel;

public interface PeliculasRepository extends JpaRepository<PeliculasModel, Long> {

}