package com.example.peliculas.service;

import java.util.List;
import java.util.Optional;

import com.example.peliculas.model.PeliculasModel;

public interface PeliculasService{

    List<PeliculasModel> getPeliculas();

    Optional<PeliculasModel> getPeliculaById(Long id);

    PeliculasModel createPelicula(PeliculasModel pelicula);

    PeliculasModel updatePelicula(Long id, PeliculasModel pelicula);

    void deletePelicula(Long id);
} 