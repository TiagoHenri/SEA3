package br.ufg.iiisea.sea.bean;

import br.ufg.iiisea.sea.utils.MutableBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fellipe on 14/09/16.
 */
public class Palestra implements MutableBean {
    public enum Tipo {
        PALESTRA(1, "palestra"),
        MINICURSO(2, "minicurso"),
        OUTROS(3, "outros"),
        UNDEFINED(4, "undefined");

        int i;
        String nome;
        Tipo(int i, String nome) {
            this.i = i;
            this.nome = nome;
        }
    }

    private long id;
    private String nome;
    private String descricao;
    private String lugar;
    private Programacao programacao;
    private Date horaInicio;
    private Date horaFim;
    private Tipo tipo;

    private List<Palestrante> palestrantes;

    public Palestra() {
    }

    public Palestra(long id, String nome, String descricao, String lugar, Programacao programacao, Date horaInicio, Date horaFim, Tipo tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.lugar = lugar;
        this.programacao = programacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.tipo = tipo;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Programacao getProgramacao() {
        return programacao;
    }

    public void setProgramacao(Programacao programacao) {
        this.programacao = programacao;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public List<Palestrante> getPalestrantes() {
        return palestrantes;
    }

    public void setPalestrantes(List<Palestrante> palestrantes) {
        this.palestrantes = palestrantes;
    }
}
