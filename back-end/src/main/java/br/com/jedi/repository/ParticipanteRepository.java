package br.com.jedi.repository;

import br.com.jedi.models.Participante;
import br.com.jedi.models.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    List<Participante> findByNome(String nomeParticipante);

    List<Participante> findAllByOrderByIdDesc();
}
