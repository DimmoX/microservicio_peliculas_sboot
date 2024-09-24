package com.example.peliculas.controllers;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.peliculas.exceptionHandler.ResourceNotFoundException;
import com.example.peliculas.model.PeliculasModel;
import com.example.peliculas.service.PeliculasService;


@RestController
@RequestMapping("/peliculas")
public class PeliculasController {

    @Autowired
    private PeliculasService peliculasService;

    @GetMapping
    public List<EntityModel<PeliculasModel>> getPeliculas() {
        List<PeliculasModel> peliculas = peliculasService.getPeliculas();
        return peliculas.stream()
                .map(this::toEntityModel)
                .collect(Collectors.toList());
    }

     @GetMapping("/{id}")
    public EntityModel<PeliculasModel> getPeliculaById(@PathVariable Long id) {
        Optional<PeliculasModel> pelicula = peliculasService.getPeliculaById(id);

        if(pelicula.isPresent()) {
            return EntityModel.of(pelicula.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculas()).withRel("peliculas"));
        } else {
            throw new ResourceNotFoundException("Pelicula not found");

        }
    }

    @PostMapping("/create")
   public EntityModel<PeliculasModel> createPelicula(@RequestBody PeliculasModel pelicula) {

        PeliculasModel createdPelicula = peliculasService.createPelicula(pelicula);
        return EntityModel.of(createdPelicula,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(createdPelicula.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculas()).withRel("peliculas"));
    }

    @PutMapping("/{id}")
    public EntityModel<PeliculasModel> updatePelicula(@PathVariable Long id, @RequestBody PeliculasModel pelicula) {
        PeliculasModel updatedPelicula = peliculasService.updatePelicula(id, pelicula);
        return toEntityModel(updatedPelicula);
    }

    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable Long id) {
        peliculasService.deletePelicula(id);
    }

    private EntityModel<PeliculasModel> toEntityModel(PeliculasModel pelicula) {
        return EntityModel.of(pelicula,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(pelicula.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculas()).withRel("peliculas"));
    }
}
