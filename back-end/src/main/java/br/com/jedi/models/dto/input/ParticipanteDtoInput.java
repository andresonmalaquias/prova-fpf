package br.com.jedi.models.dto.input;

import br.com.jedi.models.Participante;
import br.com.jedi.models.Projeto;
import br.com.jedi.repository.ParticipanteRepository;
import br.com.jedi.repository.ProjetoRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ParticipanteDtoInput {

    private Long id;

    @NotEmpty
    @Length(min = 2)
    private String nome;

    @NotNull
    @JsonFormat(pattern="dd/MM/yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Date dataNascimento;

    @NotEmpty
    @Email
    private String email;

    //converte o objeto de entrada em um objeto Participante
    public Participante converter() {
        Participante participante = new Participante();
        participante.setNome(this.nome);
        participante.setDataNascimento(this.dataNascimento);
        participante.setEmail(this.email);

        return participante;
    }

    //localiza o objeto desejado e altera seus valores
    public Participante atualizar(Long id, ParticipanteRepository participanteRepository) {
        Participante participante = participanteRepository.findById(id).orElseThrow();
        participante.setNome(this.nome);
        participante.setDataNascimento(this.dataNascimento);
        participante.setEmail(this.email);

        return participante;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
