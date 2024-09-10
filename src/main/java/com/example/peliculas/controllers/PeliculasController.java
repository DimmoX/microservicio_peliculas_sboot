package com.example.peliculas.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.peliculas.model.PeliculasModel;
import com.example.peliculas.service.PeliculasService;


@RestController
@RequestMapping("/peliculas")
public class PeliculasController {

    @Autowired
    private PeliculasService peliculasService;

    @GetMapping
    public List<PeliculasModel> getPeliculas() {
        return peliculasService.getPeliculas();
    }

    @GetMapping("/{id}")
    // Obtener pelicula por id desde el metodo getForId de la clase Peliculas.
    public Optional<PeliculasModel> getPeliculaById(@PathVariable Long id) {
        return peliculasService.getPeliculaById(id);
    }

    @PostMapping("/create")
    public PeliculasModel createPelicula(@RequestBody PeliculasModel pelicula) {
        return peliculasService.createPelicula(pelicula);
    }

    @PutMapping("/{id}")
    public PeliculasModel updatePelicula(@PathVariable Long id, @RequestBody PeliculasModel pelicula) {
        return peliculasService.updatePelicula(id, pelicula);
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id) {
        peliculasService.deletePelicula(id);
    }
}
