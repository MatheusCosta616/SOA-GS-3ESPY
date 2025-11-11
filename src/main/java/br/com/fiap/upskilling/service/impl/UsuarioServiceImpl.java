package br.com.fiap.upskilling.service.impl;

import br.com.fiap.upskilling.dto.UsuarioRequestDTO;
import br.com.fiap.upskilling.dto.UsuarioResponseDTO;
import br.com.fiap.upskilling.exception.ResourceNotFoundException;
import br.com.fiap.upskilling.model.Usuario;
import br.com.fiap.upskilling.repository.UsuarioRepository;
import br.com.fiap.upskilling.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<UsuarioResponseDTO> criar(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setAreaAtuacao(dto.getAreaAtuacao());
        usuario.setNivelCarreira(dto.getNivelCarreira());

        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(salvo));
    }

    @Override
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(
                usuarioRepository.findAll()
                        .stream()
                        .map(this::toResponseDTO)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
        return ResponseEntity.ok(toResponseDTO(usuario));
    }

    @Override
    public ResponseEntity<UsuarioResponseDTO> atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setAreaAtuacao(dto.getAreaAtuacao());
        usuario.setNivelCarreira(dto.getNivelCarreira());

        Usuario atualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(toResponseDTO(atualizado));
    }

    @Override
    public ResponseEntity<?> remover(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com id: " + id);
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getAreaAtuacao(),
                usuario.getNivelCarreira(),
                usuario.getDataCadastro()
        );
    }
}
