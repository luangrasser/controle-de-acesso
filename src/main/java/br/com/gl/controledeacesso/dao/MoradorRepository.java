package br.com.gl.controledeacesso.dao;

import br.com.gl.controledeacesso.model.Morador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoradorRepository extends JpaRepository<Morador, Integer> {

}
