package br.com.gl.controledeacesso.controller;

import br.com.gl.controledeacesso.dao.MoradorRepository;
import br.com.gl.controledeacesso.model.Morador;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@RestController
@RequestMapping("/morador")
public class MoradorController {

    @Autowired
    private MoradorRepository repository;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody Morador morador) {
        try {
            if (morador.getDataCadastro() == null) {
                morador.setDataCadastro(LocalDateTime.now());
            }
            return ResponseEntity.ok(repository.save(morador));
        } catch (Exception e) {
            log.error("Falha ao salvar morador.", e);
            return ResponseEntity.badRequest().body("Falha ao salvar morador.");
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            log.error("Falha ao pesquisar moradores.", e);
            return ResponseEntity.badRequest().body("Falha ao pesquisar moradores.");
        }
    }

    @DeleteMapping("/apagar")
    public ResponseEntity<?> apagar(@RequestParam(value = "id") Integer id) {
        try {
            Morador morador = repository.findById(id).orElseThrow(() -> new Exception("Morador desconhecido."));
            repository.delete(morador);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Falha ao apagar morador.", e);
            return ResponseEntity.badRequest().body("Falha ao apagar morador.");
        }
    }
}
