package com.example.peliculas;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeliculasController {
    
    private List<Peliculas> peliculas = new ArrayList<>();

    public PeliculasController() {
        peliculas.add(new Peliculas(1, "Titanic", 1997, "James Cameron", "Drama", "Un joven artista y un joven aristócrata se enamoran a bordo del primer y mítico viaje del trasatlántico Titanic."));
        peliculas.add(new Peliculas(2, "Avatar", 2009, "James Cameron", "Ciencia ficción", "En el año 2154, Jake Sully, un ex-marine condenado a vivir en una silla de ruedas, sigue siendo un auténtico guerrero. "));
        peliculas.add(new Peliculas(3, "El rey león", 1994, "Roger Allers", "Animación", "Un joven león llamado Simba, príncipe de las tierras, es injustamente acusado de la muerte de su padre. "));
        peliculas.add(new Peliculas(4, "El padrino", 1972, "Francis Ford Coppola", "Drama", "Don Vito Corleone, conocido dentro de los círculos del hampa como El Padrino, es el patriarca de la familia mafiosa Corleone."));
        peliculas.add(new Peliculas(5, "El señor de los anillos: El retorno del rey", 2003, "Peter Jackson", "Fantasía", "Las fuerzas de Saruman han sido destruidas, y su fortaleza sitiada. Ha llegado el momento de que se decida el destino de la Tierra Media."));
    }

    @GetMapping("/peliculas")
    public List<Peliculas> getPeliculas() {
        return peliculas;
    }

    @GetMapping("/peliculas/{id}")
    // Obtener pelicula por id desde el metodo getForId de la clase Peliculas.
    public Peliculas getPeliculaById(@PathVariable int id) {
        for(Peliculas pelicula: peliculas) {
            if(pelicula.getId() == id) {
                return pelicula;
            }
        }

        return null;
    }
}
