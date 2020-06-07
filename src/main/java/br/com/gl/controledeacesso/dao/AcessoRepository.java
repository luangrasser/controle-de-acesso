package br.com.gl.controledeacesso.dao;


import br.com.gl.controledeacesso.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Integer> {
}
