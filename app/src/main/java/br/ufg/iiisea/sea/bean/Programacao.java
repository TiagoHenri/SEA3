package br.ufg.iiisea.sea.bean;

import br.ufg.iiisea.sea.utils.MutableBean;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by fellipe on 14/09/16.
 */
public class Programacao implements MutableBean {
    private long id;
    private Date dia;
    private String descricao;
    private Evento evento;

    public Programacao() {
    }

    public Programacao(long id, Date dia, String descricao, Evento evento) {
        this.id = id;
        this.dia = dia;
        this.descricao = descricao;
        this.evento = evento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
