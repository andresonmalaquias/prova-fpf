package br.com.jedi.models.dto.input;

import br.com.jedi.models.Projeto;
import br.com.jedi.repository.ProjetoRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

public class ProjetoDtoInput {

    private Long id;

    @NotEmpty
    @Length(min = 2)
    private String nome;

    @NotNull
    @JsonFormat(pattern="dd/MM/yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Date dataInicio;

    @NotNull
    @JsonFormat(pattern="dd/MM/yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private Date dataTermino;

    @NotNull
    private Double valor;

    @NotNull
    private Integer risco;

    //converte o objeto de entrada em um objeto Projeto
    public Projeto converter() {
        Projeto projeto = new Projeto();
        projeto.setNome(this.nome);
        projeto.setDataInicio(this.dataInicio);
        projeto.setDataTermino(this.dataTermino);
        projeto.setValor(this.valor);
        projeto.setRisco(this.risco);
        return projeto;
    }

    //localiza o objeto desejado e altera seus valores
    public Projeto atualizar(Long id, ProjetoRepository projetoRepository) {
        Projeto projeto = projetoRepository.findById(id).orElseThrow();
        projeto.setNome(this.nome);
        projeto.setDataInicio(this.dataInicio);
        projeto.setDataTermino(this.dataTermino);
        projeto.setValor(this.valor);
        projeto.setRisco(this.risco);

        return projeto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setRisco(Integer risco) {
        this.risco = risco;
    }
}
