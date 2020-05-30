package br.com.gl.controledeacesso.dao;

import br.com.gl.controledeacesso.model.PrestadorServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestadorServicoRepository extends JpaRepository<PrestadorServico, Integer> {
}
