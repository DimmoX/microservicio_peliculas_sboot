package com.example.peliculas.exceptionHandler;

/**
 * @description Clase que maneja las excepciones de recursos no encontrados
 */
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
