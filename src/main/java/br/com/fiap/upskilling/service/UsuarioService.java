package br.com.fiap.upskilling.service;

import br.com.fiap.upskilling.dto.UsuarioRequestDTO;
import br.com.fiap.upskilling.dto.UsuarioResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioService {
    
    ResponseEntity<UsuarioResponseDTO> criar(UsuarioRequestDTO dto);
    
    ResponseEntity<List<UsuarioResponseDTO>> listarTodos();
    
    ResponseEntity<UsuarioResponseDTO> buscarPorId(Long id);
    
    ResponseEntity<UsuarioResponseDTO> atualizar(Long id, UsuarioRequestDTO dto);
    
    ResponseEntity<?> remover(Long id);
}
