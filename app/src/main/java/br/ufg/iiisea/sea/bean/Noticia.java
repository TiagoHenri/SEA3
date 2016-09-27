package br.ufg.iiisea.sea.bean;

import br.ufg.iiisea.sea.utils.ListableBean;

import java.util.Date;

/**
 * Created by fellipe on 22/09/16.
 */
public class Noticia implements ListableBean {

    private long objectId;
    private String titulo;
    private Date data;
    private String conteudo;
    private Evento evento;

    public Noticia() {

    }

    public Noticia(long objectId, String titulo) {
        this.objectId = objectId;
        this.titulo = titulo;
    }

    public Noticia(int objectId, String titulo, Evento evento, Date data, String conteudo) {
        this.objectId = objectId;
        this.titulo = titulo;
        this.evento = evento;
        this.data = data;
        this.conteudo = conteudo;
    }

    public Noticia(int objectId) {
        this.objectId = objectId;
    }

    public long getId() {
        return objectId;
    }

    public void setId(long objectId) {
        this.objectId = objectId;
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
}
