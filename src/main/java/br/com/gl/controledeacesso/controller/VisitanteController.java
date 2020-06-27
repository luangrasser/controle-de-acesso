package br.com.gl.controledeacesso.controller;

import br.com.gl.controledeacesso.dao.VisitanteRepository;
import br.com.gl.controledeacesso.model.Visitante;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@RestController
@RequestMapping("/visitante")
public class VisitanteController {

    @Autowired
    private VisitanteRepository repository;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody Visitante visitante) {
        try {
            if (visitante.getDataCadastro() == null) {
                visitante.setDataCadastro(LocalDateTime.now());
            }
            return ResponseEntity.ok(repository.save(visitante));
        } catch (Exception e) {
            log.error("Falha ao salvar visitante.", e);
            return ResponseEntity.badRequest().body("Falha ao salvar visitante.");
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            log.error("Falha ao pesquisar visitantes.", e);
            return ResponseEntity.badRequest().body("Falha ao pesquisar visitantes.");
        }
    }

    @DeleteMapping("/apagar")
    public ResponseEntity<?> apagar(@RequestParam(value = "id") Integer id) {
        try {
            Visitante visitante = repository.findById(id).orElseThrow(() -> new Exception("Visitante desconhecido."));
            repository.delete(visitante);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Falha ao apagar visitante.", e);
            return ResponseEntity.badRequest().body("Falha ao apagar visitante.");
        }
    }
}
