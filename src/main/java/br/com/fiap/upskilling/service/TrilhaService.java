package br.com.fiap.upskilling.service;

import br.com.fiap.upskilling.dto.TrilhaRequestDTO;
import br.com.fiap.upskilling.dto.TrilhaResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrilhaService {
    
    ResponseEntity<TrilhaResponseDTO> criar(TrilhaRequestDTO dto);
    
    ResponseEntity<List<TrilhaResponseDTO>> listarTodas();
    
    ResponseEntity<TrilhaResponseDTO> buscarPorId(Long id);
    
    ResponseEntity<TrilhaResponseDTO> atualizar(Long id, TrilhaRequestDTO dto);
    
    ResponseEntity<?> remover(Long id);
}
