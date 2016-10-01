package br.ufg.iiisea.sea.bean;

import android.support.annotation.NonNull;
import br.ufg.iiisea.sea.utils.MutableBean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fellipe on 14/09/16.
 */
public class Programacao implements MutableBean, Serializable {
    private long id = 0;
    private Date data;
    private String descricao;
    private Evento evento;

    public Programacao() {
    }

    public Programacao(long id, Date dia, String descricao, Evento evento) {
        this.id = id;
        this.data = dia;
        this.descricao = descricao;
        this.evento = evento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
