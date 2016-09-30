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
        PALESTRA {
            public int getId() {
                return 1;
            }
            @Override
            public String toString() {
                return "Palestra";
            }
        },
        MINICURSO {
            public int getId() {
                return 0;
            }
            @Override
            public String toString() {
                return "Minicurso";
            }
        }
    }

    private long id;
    private String nome;
    private String descricao;
    private String lugar;
    private Programacao programacao;
    private Date hora_inicio;
    private Date hora_fim;
    private Tipo tipo;

    private List<Palestrante> palestrantes;

    public Palestra(long id, String nome, String descricao, String lugar, Programacao programacao, Date hora_inicio, Date hora_fim, Tipo tipo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.lugar = lugar;
        this.programacao = programacao;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.tipo = tipo;
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

    public Date getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Date getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(Date hora_fim) {
        this.hora_fim = hora_fim;
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
