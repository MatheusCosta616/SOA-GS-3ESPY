package br.com.fiap.upskilling.service.impl;

import br.com.fiap.upskilling.dto.TrilhaRequestDTO;
import br.com.fiap.upskilling.dto.TrilhaResponseDTO;
import br.com.fiap.upskilling.exception.ResourceNotFoundException;
import br.com.fiap.upskilling.model.Trilha;
import br.com.fiap.upskilling.repository.TrilhaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrilhaServiceImplTest {

    @Mock
    private TrilhaRepository trilhaRepository;

    @InjectMocks
    private TrilhaServiceImpl trilhaService;

    private Trilha trilha;
    private TrilhaRequestDTO trilhaRequestDTO;

    @BeforeEach
    void setUp() {
        trilha = new Trilha();
        trilha.setId(1L);
        trilha.setNome("Java Avançado");
        trilha.setDescricao("Trilha de Java para desenvolvedores experientes");
        trilha.setNivel("AVANCADO");
        trilha.setCargaHoraria(120);
        trilha.setFocoPrincipal("Backend");

        trilhaRequestDTO = new TrilhaRequestDTO(
                "Java Avançado",
                "Trilha de Java para desenvolvedores experientes",
                "AVANCADO",
                120,
                "Backend"
        );
    }

    @Test
    void testCriar() {
        when(trilhaRepository.save(any(Trilha.class))).thenReturn(trilha);

        ResponseEntity<TrilhaResponseDTO> response = trilhaService.criar(trilhaRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Java Avançado", response.getBody().getNome());
        assertEquals("AVANCADO", response.getBody().getNivel());
        assertEquals(120, response.getBody().getCargaHoraria());
        verify(trilhaRepository, times(1)).save(any(Trilha.class));
    }

    @Test
    void testListarTodas() {
        Trilha trilha2 = new Trilha();
        trilha2.setId(2L);
        trilha2.setNome("Python para Data Science");
        trilha2.setDescricao("Aprenda Python aplicado à ciência de dados");
        trilha2.setNivel("INTERMEDIARIO");
        trilha2.setCargaHoraria(80);
        trilha2.setFocoPrincipal("Data Science");

        when(trilhaRepository.findAll()).thenReturn(Arrays.asList(trilha, trilha2));

        ResponseEntity<List<TrilhaResponseDTO>> response = trilhaService.listarTodas();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Java Avançado", response.getBody().get(0).getNome());
        assertEquals("Python para Data Science", response.getBody().get(1).getNome());
        verify(trilhaRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        when(trilhaRepository.findById(1L)).thenReturn(Optional.of(trilha));

        ResponseEntity<TrilhaResponseDTO> response = trilhaService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Java Avançado", response.getBody().getNome());
        assertEquals("AVANCADO", response.getBody().getNivel());
        verify(trilhaRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorIdNaoEncontrado() {
        when(trilhaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            trilhaService.buscarPorId(999L);
        });

        verify(trilhaRepository, times(1)).findById(999L);
    }

    @Test
    void testAtualizar() {
        when(trilhaRepository.findById(1L)).thenReturn(Optional.of(trilha));
        when(trilhaRepository.save(any(Trilha.class))).thenReturn(trilha);

        TrilhaRequestDTO atualizadoDTO = new TrilhaRequestDTO(
                "Java Avançado Atualizado",
                "Descrição atualizada",
                "AVANCADO",
                150,
                "Backend"
        );

        ResponseEntity<TrilhaResponseDTO> response = trilhaService.atualizar(1L, atualizadoDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(trilhaRepository, times(1)).findById(1L);
        verify(trilhaRepository, times(1)).save(any(Trilha.class));
    }

    @Test
    void testAtualizarNaoEncontrado() {
        when(trilhaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            trilhaService.atualizar(999L, trilhaRequestDTO);
        });

        verify(trilhaRepository, times(1)).findById(999L);
        verify(trilhaRepository, never()).save(any(Trilha.class));
    }

    @Test
    void testRemover() {
        when(trilhaRepository.existsById(1L)).thenReturn(true);
        doNothing().when(trilhaRepository).deleteById(anyLong());

        ResponseEntity<?> response = trilhaService.remover(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(trilhaRepository, times(1)).existsById(1L);
        verify(trilhaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRemoverNaoEncontrado() {
        when(trilhaRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            trilhaService.remover(999L);
        });

        verify(trilhaRepository, times(1)).existsById(999L);
        verify(trilhaRepository, never()).deleteById(anyLong());
    }
}
