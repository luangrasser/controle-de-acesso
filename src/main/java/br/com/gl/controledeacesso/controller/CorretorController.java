package br.com.gl.controledeacesso.controller;

import br.com.gl.controledeacesso.dao.CorretorRepository;
import br.com.gl.controledeacesso.model.Corretor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@RestController
@RequestMapping("/corretor")
public class CorretorController {

    @Autowired
    private CorretorRepository repository;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody Corretor corretor) {
        try {
            if (corretor.getDataCadastro() == null) {
                corretor.setDataCadastro(LocalDateTime.now());
            }
            return ResponseEntity.ok(repository.save(corretor));
        } catch (Exception e) {
            log.error("Falha ao salvar corretor.", e);
            return ResponseEntity.badRequest().body("Falha ao salvar corretor.");
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            log.error("Falha ao pesquisar corretores.", e);
            return ResponseEntity.badRequest().body("Falha ao pesquisar corretores.");
        }
    }

    @DeleteMapping("/apagar")
    public ResponseEntity<?> apagar(@RequestParam(value = "id") Integer id) {
        try {
            Corretor corretor = repository.findById(id).orElseThrow(() -> new Exception("Corretor desconhecido."));
            repository.delete(corretor);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Falha ao apagar corretor.", e);
            return ResponseEntity.badRequest().body("Falha ao apagar corretor.");
        }
    }
}
