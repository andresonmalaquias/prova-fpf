package br.com.jedi.controllers;

import br.com.jedi.models.Projeto;
import br.com.jedi.models.dto.input.ProjetoDtoInput;
import br.com.jedi.models.dto.output.ProjetoDtoOutput;
import br.com.jedi.repository.ProjetoRepository;
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
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @GetMapping
    public List<ProjetoDtoOutput> listarProjetos(String nomeProjeto){
        List<Projeto> projetos;
        if (nomeProjeto == null){
            projetos = this.projetoRepository.findAllByOrderByIdDesc();
        }else{
            projetos = this.projetoRepository.findByNome(nomeProjeto);
        }
        return ProjetoDtoOutput.converter(projetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoDtoOutput> detalharProjeto(@PathVariable Long id){
        Optional<Projeto> projeto = this.projetoRepository.findById(id);
        if(projeto.isPresent()){
            return ResponseEntity.ok(new ProjetoDtoOutput(projeto.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProjetoDtoOutput> cadastrarProjeto(@RequestBody @Valid ProjetoDtoInput projetoInput,
                                                             UriComponentsBuilder uriBuilder){
        Projeto projeto = projetoInput.converter();
        this.projetoRepository.save(projeto);

        URI uri = uriBuilder.path("/projeto/{id}").buildAndExpand(projeto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProjetoDtoOutput(projeto));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProjetoDtoOutput> atualizarProjeto(@PathVariable Long id,
                                                             @RequestBody @Valid ProjetoDtoInput projetoInput){
        Optional<Projeto> optional = this.projetoRepository.findById(id);
        if(optional.isPresent()){
            Projeto projeto = projetoInput.atualizar(id, this.projetoRepository);
            return ResponseEntity.ok(new ProjetoDtoOutput(projeto));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerProjeto(@PathVariable Long id){
        Optional<Projeto> optional = this.projetoRepository.findById(id);
        if(optional.isPresent()){
            this.projetoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
