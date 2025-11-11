package br.com.fiap.upskilling.service.impl;

import br.com.fiap.upskilling.dto.UsuarioRequestDTO;
import br.com.fiap.upskilling.dto.UsuarioResponseDTO;
import br.com.fiap.upskilling.exception.ResourceNotFoundException;
import br.com.fiap.upskilling.model.Usuario;
import br.com.fiap.upskilling.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private Usuario usuario;
    private UsuarioRequestDTO usuarioRequestDTO;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Carlos Santos");
        usuario.setEmail("carlos@example.com");
        usuario.setAreaAtuacao("Desenvolvimento");
        usuario.setNivelCarreira("PLENO");
        usuario.setDataCadastro(LocalDate.now());

        usuarioRequestDTO = new UsuarioRequestDTO(
                "Carlos Santos",
                "carlos@example.com",
                "Desenvolvimento",
                "PLENO"
        );
    }

    @Test
    void testCriar() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<UsuarioResponseDTO> response = usuarioService.criar(usuarioRequestDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Carlos Santos", response.getBody().getNome());
        assertEquals("carlos@example.com", response.getBody().getEmail());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testListarTodos() {
        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNome("Maria Oliveira");
        usuario2.setEmail("maria@example.com");
        usuario2.setAreaAtuacao("Design");
        usuario2.setNivelCarreira("SENIOR");
        usuario2.setDataCadastro(LocalDate.now());

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario, usuario2));

        ResponseEntity<List<UsuarioResponseDTO>> response = usuarioService.listarTodos();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Carlos Santos", response.getBody().get(0).getNome());
        assertEquals("Maria Oliveira", response.getBody().get(1).getNome());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        ResponseEntity<UsuarioResponseDTO> response = usuarioService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Carlos Santos", response.getBody().getNome());
        assertEquals("carlos@example.com", response.getBody().getEmail());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testBuscarPorIdNaoEncontrado() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            usuarioService.buscarPorId(999L);
        });

        verify(usuarioRepository, times(1)).findById(999L);
    }

    @Test
    void testAtualizar() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        UsuarioRequestDTO atualizadoDTO = new UsuarioRequestDTO(
                "Carlos Santos Atualizado",
                "carlos.novo@example.com",
                "Desenvolvimento",
                "SENIOR"
        );

        ResponseEntity<UsuarioResponseDTO> response = usuarioService.atualizar(1L, atualizadoDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(usuarioRepository, times(1)).findById(1L);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testAtualizarNaoEncontrado() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            usuarioService.atualizar(999L, usuarioRequestDTO);
        });

        verify(usuarioRepository, times(1)).findById(999L);
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void testRemover() {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(anyLong());

        ResponseEntity<?> response = usuarioService.remover(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioRepository, times(1)).existsById(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRemoverNaoEncontrado() {
        when(usuarioRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            usuarioService.remover(999L);
        });

        verify(usuarioRepository, times(1)).existsById(999L);
        verify(usuarioRepository, never()).deleteById(anyLong());
    }
}
