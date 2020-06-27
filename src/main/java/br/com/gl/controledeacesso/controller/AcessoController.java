package br.com.gl.controledeacesso.controller;

import br.com.gl.controledeacesso.dao.*;
import br.com.gl.controledeacesso.form.AcessoForm;
import br.com.gl.controledeacesso.model.Acesso;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/acesso")
public class AcessoController {

    @Autowired
    private AcessoRepository repository;
    @Autowired
    private MoradorRepository moradorRepository;
    @Autowired
    private VisitanteRepository visitanteRepository;
    @Autowired
    private PrestadorServicoRepository prestadorRepository;
    @Autowired
    private CorretorRepository corretorRepository;
    @Autowired
    private EntregadorRepository entregadorRepository;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody AcessoForm form) {
        try {
            Acesso acesso = form.transform(moradorRepository, visitanteRepository, prestadorRepository, corretorRepository, entregadorRepository);
            return ResponseEntity.ok(repository.save(acesso));
        } catch (Exception e) {
            log.error("Falha ao salvar acesso.", e);
            return ResponseEntity.badRequest().body("Falha ao salvar acesso.");
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar() {
        try {
            return ResponseEntity.ok(repository.findAll());
        } catch (Exception e) {
            log.error("Falha ao pesquisar acessos.", e);
            return ResponseEntity.badRequest().body("Falha ao pesquisar acessos.");
        }
    }

    @DeleteMapping("/apagar")
    public ResponseEntity<?> apagar(@RequestParam(value = "id") Integer id) {
        try {
            Acesso acesso = repository.findById(id).orElseThrow(() -> new Exception("Acesso desconhecido."));
            repository.delete(acesso);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Falha ao apagar acesso.", e);
            return ResponseEntity.badRequest().body("Falha ao apagar acesso.");
        }
    }
}