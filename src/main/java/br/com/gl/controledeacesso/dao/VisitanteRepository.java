package br.com.gl.controledeacesso.dao;

import br.com.gl.controledeacesso.model.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Integer> {
}
