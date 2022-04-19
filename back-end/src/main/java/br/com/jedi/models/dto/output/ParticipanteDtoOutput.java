package br.com.jedi.models.dto.output;

import br.com.jedi.models.Participante;
import br.com.jedi.models.Projeto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipanteDtoOutput {

    private Long id;

    private String nome;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataNascimento;

    private String email;

    //converte o Objeto Participante para o DTO
    public ParticipanteDtoOutput(Participante participante) {
        this.id = participante.getId();
        this.nome = participante.getNome();
        this.dataNascimento = participante.getDataNascimento();
        this.email = participante.getEmail();
    }

    //converte a lista de participantes para a lista de participantesDtoOutput
    public static List<ParticipanteDtoOutput> converter(List<Participante> participantes) {
        return participantes.stream().map(ParticipanteDtoOutput::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }
}
