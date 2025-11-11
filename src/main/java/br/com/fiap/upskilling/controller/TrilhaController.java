package br.com.fiap.upskilling.controller;

import br.com.fiap.upskilling.dto.TrilhaRequestDTO;
import br.com.fiap.upskilling.dto.TrilhaResponseDTO;
import br.com.fiap.upskilling.service.TrilhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trilhas")
@Tag(name = "Trilhas", description = "Endpoints para gerenciamento de trilhas de aprendizagem")
public class TrilhaController {

    @Autowired
    private TrilhaService trilhaService;

    @PostMapping
    @Operation(summary = "Criar nova trilha", description = "Cria uma nova trilha de aprendizagem no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Trilha criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<TrilhaResponseDTO> criar(@Valid @RequestBody TrilhaRequestDTO dto) {
        return trilhaService.criar(dto);
    }

    @GetMapping
    @Operation(summary = "Listar todas as trilhas", description = "Retorna uma lista com todas as trilhas de aprendizagem cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    public ResponseEntity<List<TrilhaResponseDTO>> listarTodas() {
        return trilhaService.listarTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar trilha por ID", description = "Retorna uma trilha específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trilha encontrada"),
            @ApiResponse(responseCode = "404", description = "Trilha não encontrada")
    })
    public ResponseEntity<TrilhaResponseDTO> buscarPorId(@PathVariable Long id) {
        return trilhaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar trilha", description = "Atualiza os dados de uma trilha existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trilha atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Trilha não encontrada")
    })
    public ResponseEntity<TrilhaResponseDTO> atualizar(@PathVariable Long id,
                                                       @Valid @RequestBody TrilhaRequestDTO dto) {
        return trilhaService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover trilha", description = "Remove uma trilha do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Trilha removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Trilha não encontrada")
    })
    public ResponseEntity<?> remover(@PathVariable Long id) {
        trilhaService.remover(id);
        return ResponseEntity.noContent().build();
    }
}