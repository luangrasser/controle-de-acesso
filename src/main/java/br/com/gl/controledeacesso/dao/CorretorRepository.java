package br.com.gl.controledeacesso.dao;

import br.com.gl.controledeacesso.model.Corretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorretorRepository extends JpaRepository<Corretor, Integer> {
}
