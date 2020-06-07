package br.com.gl.controledeacesso.controller;

import br.com.gl.controledeacesso.dao.PrestadorServicoRepository;
import br.com.gl.controledeacesso.model.PrestadorServico;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/prestadorservico")
public class PrestadorServicoController {

    @Autowired
    private PrestadorServicoRepository repository;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody PrestadorServico prestadorServico) {
        try {
            return ResponseEntity.ok(repository.save(prestadorServico));
        } catch (Exception e) {
            log.error("Falha ao salvar prestador de serviço.", e);
            return ResponseEntity.badRequest().body("Falha ao salvar prestador de serviço.");
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            log.error("Falha ao pesquisar prestadores de serviço.", e);
            return ResponseEntity.badRequest().body("Falha ao prestadores de serviço.");
        }
    }

    @DeleteMapping("/apagar")
    public ResponseEntity<?> apagar(@RequestParam(value = "id") Integer id) {
        try {
            PrestadorServico prestadorServico = repository.findById(id).orElseThrow(() -> new Exception("Prestador de serviço desconhecido."));
            repository.delete(prestadorServico);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Falha ao apagar prestador de serviço.", e);
            return ResponseEntity.badRequest().body("Falha ao prestador de serviço.");
        }
    }
}
