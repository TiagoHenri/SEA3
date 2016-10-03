package br.ufg.iiisea.sea.bean;

import android.support.annotation.NonNull;
import br.ufg.iiisea.sea.utils.ListableBean;
import br.ufg.iiisea.sea.utils.MutableBean;

import java.util.Date;

/**
 * Created by fellipe on 22/09/16.
 */
public class Noticia implements ListableBean, MutableBean {

    private long id;
    private String titulo;
    private Date data;
    private String conteudo;
    private Evento evento;

    public Noticia() {

    }

    public Noticia(long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Noticia(long id, String titulo, Date data, String conteudo, Evento evento) {
        this.id = id;
        this.titulo = titulo;
        this.data = data;
        this.conteudo = conteudo;
        this.evento = evento;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Noticia noticia = (Noticia) o;

        if (id != noticia.id) return false;
        if (titulo != null ? !titulo.equals(noticia.titulo) : noticia.titulo != null) return false;
        if (data != null ? !data.equals(noticia.data) : noticia.data != null) return false;
        if (conteudo != null ? !conteudo.equals(noticia.conteudo) : noticia.conteudo != null) return false;
        return evento != null ? evento.equals(noticia.evento) : noticia.evento == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (conteudo != null ? conteudo.hashCode() : 0);
        result = 31 * result + (evento != null ? evento.hashCode() : 0);
        return result;
    }


    @Override
    public int compareTo(@NonNull Object o) {
        Noticia noticia = (Noticia) o;
        if (getData() == null || noticia.getData() == null)
            return 0;
        return -1*getData().compareTo(noticia.getData());
    }
}
