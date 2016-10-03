package br.ufg.iiisea.sea.bean;

import android.support.annotation.NonNull;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Evento evento = (Evento) o;

        return this.id == evento.getId();
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + nome.hashCode();
        result = 31 * result + descricao.hashCode();
        result = 31 * result + dataInicio.hashCode();
        result = 31 * result + dataFim.hashCode();
        return result;
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

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
