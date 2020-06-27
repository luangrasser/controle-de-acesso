package br.com.gl.controledeacesso.dao;

import br.com.gl.controledeacesso.model.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Integer> {
}
