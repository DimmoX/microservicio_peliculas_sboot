package com.example.peliculas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.peliculas.model.PeliculasModel;
import com.example.peliculas.repository.PeliculasRepository;

@ExtendWith(MockitoExtension.class)
public class PeliculasServiceTest {

    @InjectMocks
    private PeliculasServiceImpl peliculasService;

    @Mock
    private PeliculasRepository peliculasRepositoryMock;

    private PeliculasModel peliculasModel;

    // Este método se ejecutará antes de cada test
    @BeforeEach
    public void configuracionInicial() {

        //** Se inicialilza objeto model en cada test.
        peliculasModel = new PeliculasModel();
        peliculasModel.setId(1L);
        peliculasModel.setTitulo("Titanic");
        peliculasModel.setDirector("James Cameron");
        peliculasModel.setGenero("Romance");
        peliculasModel.setAnio(1997);
        peliculasModel.setSinopsis("Un joven pobre y una joven rica se enamoran en el Titanic, pero el barco se hunde y ella muere.");

        //** Se configura mock para los métodos existsById y save del repositorio.
        when(peliculasRepositoryMock.existsById(1L)).thenReturn(true);
        when(peliculasRepositoryMock.save(any(PeliculasModel.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @AfterEach
    public void limpiarRecursos() {

        //** Se realiza limpieza de objeto model en cada test.
        peliculasModel = null;

        //** se realiza reset de mock en cada test.
        Mockito.reset(peliculasRepositoryMock);
    }

    @Test
    @DisplayName("Test para crear una película")
    public void createPeliculaTest() {

        //** Se simula el método save del repositorio para que devuelva el objeto peliculasModel.
        when(peliculasRepositoryMock.save(any(PeliculasModel.class))).thenReturn(peliculasModel);

        PeliculasModel result = peliculasService.createPelicula(peliculasModel);

        assertNotNull(result);
        assertEquals("Titanic", result.getTitulo());
        assertEquals("James Cameron", result.getDirector());
        assertEquals("Romance", result.getGenero());
        assertEquals(1997, result.getAnio());
        assertEquals("Un joven pobre y una joven rica se enamoran en el Titanic, pero el barco se hunde y ella muere.", result.getSinopsis());

        verify(peliculasRepositoryMock, times(1)).save(peliculasModel);
    }

    @Test
    @DisplayName("Test para actualizar una película")
    public void updatePeliculaTest() {

        //** Se genera objeto peliculasModelUpdated con los datos actualizados.
        PeliculasModel peliculasModelUpdated = new PeliculasModel();
        peliculasModelUpdated.setId(1L);
        peliculasModelUpdated.setTitulo("Titanic 2: resurrección");
        peliculasModelUpdated.setDirector("James Cameron");
        peliculasModelUpdated.setGenero("Romance");
        peliculasModelUpdated.setAnio(1997);
        peliculasModelUpdated.setSinopsis("Un joven pobre y una joven rica se enamoran en el Titanic, pero el barco se hunde y ella muere.");
        
        PeliculasModel result = peliculasService.updatePelicula(1L, peliculasModelUpdated);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Titanic 2: resurrección", result.getTitulo());
        assertEquals("James Cameron", result.getDirector());
        assertEquals("Romance", result.getGenero());
        assertEquals(1997, result.getAnio());
        assertEquals("Un joven pobre y una joven rica se enamoran en el Titanic, pero el barco se hunde y ella muere.", result.getSinopsis());

        verify(peliculasRepositoryMock, times(1)).save(peliculasModelUpdated);
    }
}
