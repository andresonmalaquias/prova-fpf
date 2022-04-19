package br.com.jedi.repository;

import br.com.jedi.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    List<Projeto> findByNome(String nomeProjeto);

    List<Projeto> findAllByOrderByIdDesc();
}
