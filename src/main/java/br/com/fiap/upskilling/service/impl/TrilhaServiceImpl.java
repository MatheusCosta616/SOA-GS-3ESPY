package br.com.fiap.upskilling.service.impl;

import br.com.fiap.upskilling.dto.TrilhaRequestDTO;
import br.com.fiap.upskilling.dto.TrilhaResponseDTO;
import br.com.fiap.upskilling.exception.ResourceNotFoundException;
import br.com.fiap.upskilling.model.Trilha;
import br.com.fiap.upskilling.repository.TrilhaRepository;
import br.com.fiap.upskilling.service.TrilhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrilhaServiceImpl implements TrilhaService {

    @Autowired
    private TrilhaRepository trilhaRepository;

    @Override
    public ResponseEntity<TrilhaResponseDTO> criar(TrilhaRequestDTO dto) {
        Trilha trilha = new Trilha();
        trilha.setNome(dto.getNome());
        trilha.setDescricao(dto.getDescricao());
        trilha.setNivel(dto.getNivel());
        trilha.setCargaHoraria(dto.getCargaHoraria());
        trilha.setFocoPrincipal(dto.getFocoPrincipal());

        Trilha salva = trilhaRepository.save(trilha);

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(salva));
    }

    @Override
    public ResponseEntity<List<TrilhaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(
            trilhaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<TrilhaResponseDTO> buscarPorId(Long id) {
        Trilha trilha = trilhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trilha não encontrada com id: " + id));
        return ResponseEntity.ok(toResponseDTO(trilha));
    }

    @Override
    public ResponseEntity<TrilhaResponseDTO> atualizar(Long id, TrilhaRequestDTO dto) {
        Trilha trilha = trilhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trilha não encontrada com id: " + id));

        trilha.setNome(dto.getNome());
        trilha.setDescricao(dto.getDescricao());
        trilha.setNivel(dto.getNivel());
        trilha.setCargaHoraria(dto.getCargaHoraria());
        trilha.setFocoPrincipal(dto.getFocoPrincipal());

        Trilha atualizada = trilhaRepository.save(trilha);
        return ResponseEntity.ok(toResponseDTO(atualizada));
    }

    @Override
    public ResponseEntity<?> remover(Long id) {
        if (!trilhaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Trilha não encontrada com id: " + id);
        }
        trilhaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private TrilhaResponseDTO toResponseDTO(Trilha trilha) {
        return new TrilhaResponseDTO(
                trilha.getId(),
                trilha.getNome(),
                trilha.getDescricao(),
                trilha.getNivel(),
                trilha.getCargaHoraria(),
                trilha.getFocoPrincipal()
        );
    }
}
