package com.example.peliculas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.peliculas.model.PeliculasModel;
import com.example.peliculas.repository.PeliculasRepository;

@Service
public class PeliculasServiceImpl implements PeliculasService {

    @Autowired
    private PeliculasRepository peliculasRepository;

    @Override
    public List<PeliculasModel> getPeliculas() {
        return peliculasRepository.findAll();
    }

    @Override
    public Optional<PeliculasModel> getPeliculaById(Long id) {
        return peliculasRepository.findById(id);
    }

    public PeliculasModel createPelicula(PeliculasModel pelicula) {
        return peliculasRepository.save(pelicula);
    }

    public void deletePelicula(Long id) {
        peliculasRepository.deleteById(id);
    }
}
