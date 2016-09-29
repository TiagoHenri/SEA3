package br.ufg.iiisea.sea.bean;

import br.ufg.iiisea.sea.utils.MutableBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by fellipe on 14/09/16.
 */
public class Evento implements Serializable, MutableBean {

    private long id;
    private String nome;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;

    public Evento() {

    }

    public Evento(long id) {
        this.id = id;
    }

    public Evento(long id, String nome, String descricao, Date dataInicio, Date dataFim) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public long getId() {
        return id;
    }

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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
