package br.com.jedi.controllers;


import br.com.jedi.models.Participante;
import br.com.jedi.models.dto.input.ParticipanteDtoInput;
import br.com.jedi.models.dto.output.ParticipanteDtoOutput;
import br.com.jedi.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participante")
public class ParticipanteController {

    @Autowired
    private ParticipanteRepository participanteRepository;

    @GetMapping
    public List<ParticipanteDtoOutput> listarParticipante(String nomeParticipante){
        List<Participante> participantes;
        if (nomeParticipante == null){
            participantes = this.participanteRepository.findAllByOrderByIdDesc();
        }else{
            participantes = this.participanteRepository.findByNome(nomeParticipante);
        }
        return ParticipanteDtoOutput.converter(participantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipanteDtoOutput> detalharParticipante(@PathVariable Long id){
        Optional<Participante> participante = this.participanteRepository.findById(id);
        if(participante.isPresent()){
            return ResponseEntity.ok(new ParticipanteDtoOutput(participante.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ParticipanteDtoOutput> cadastrarParticipante(@RequestBody @Valid ParticipanteDtoInput participanteInput,
                                                             UriComponentsBuilder uriBuilder){
        Participante participante = participanteInput.converter();
        this.participanteRepository.save(participante);

        URI uri = uriBuilder.path("/participante/{id}").buildAndExpand(participante.getId()).toUri();
        return ResponseEntity.created(uri).body(new ParticipanteDtoOutput(participante));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ParticipanteDtoOutput> atualizarParticipante(@PathVariable Long id,
                                                             @RequestBody @Valid ParticipanteDtoInput participanteInput){
        Optional<Participante> optional = this.participanteRepository.findById(id);
        if(optional.isPresent()){
            Participante participante = participanteInput.atualizar(id, this.participanteRepository);
            return ResponseEntity.ok(new ParticipanteDtoOutput(participante));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerParticipante(@PathVariable Long id){
        Optional<Participante> optional = this.participanteRepository.findById(id);
        if(optional.isPresent()){
            this.participanteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
