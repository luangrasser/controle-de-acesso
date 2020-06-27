package br.com.gl.controledeacesso.controller;

import br.com.gl.controledeacesso.dao.EntregadorRepository;
import br.com.gl.controledeacesso.model.Entregador;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@RestController
@RequestMapping("/entregador")
public class EntregadorController {

    @Autowired
    private EntregadorRepository repository;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody Entregador entregador) {
        try {
            if (entregador.getDataCadastro() == null) {
                entregador.setDataCadastro(LocalDateTime.now());
            }
            return ResponseEntity.ok(repository.save(entregador));
        } catch (Exception e) {
            log.error("Falha ao salvar entregador.", e);
            return ResponseEntity.badRequest().body("Falha ao salvar entregador.");
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            log.error("Falha ao pesquisar entregadores.", e);
            return ResponseEntity.badRequest().body("Falha ao pesquisar entregadores.");
        }
    }

    @DeleteMapping("/apagar")
    public ResponseEntity<?> apagar(@RequestParam(value = "id") Integer id) {
        try {
            Entregador entregador = repository.findById(id).orElseThrow(() -> new Exception("Entregador desconhecido."));
            repository.delete(entregador);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Falha ao apagar corretor.", e);
            return ResponseEntity.badRequest().body("Falha ao apagar corretor.");
        }
    }
}
