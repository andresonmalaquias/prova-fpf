package br.com.jedi.models.dto.output;

import br.com.jedi.models.Projeto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ProjetoDtoOutput {

    private Long id;

    private String nome;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataInicio;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataTermino;

    private Double valor;

    private Integer risco;

    //converte o Objeto Projeto para o DTO
    public ProjetoDtoOutput(Projeto projeto) {
        this.id = projeto.getId();
        this.nome = projeto.getNome();
        this.dataInicio = projeto.getDataInicio();
        this.dataTermino = projeto.getDataTermino();
        this.valor = projeto.getValor();
        this.risco = projeto.getRisco();
    }

    //converte a lista de projetos para a lista de projetoDtoOutput
    public static List<ProjetoDtoOutput> converter(List<Projeto> projetos) {
        return projetos.stream().map(ProjetoDtoOutput::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getRisco() {
        return risco;
    }
}
