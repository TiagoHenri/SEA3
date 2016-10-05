package br.ufg.iiisea.sea.bean;

import android.support.annotation.NonNull;
import android.util.Log;
import br.ufg.iiisea.sea.utils.MutableBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fellipe on 14/09/16.
 */
public class Palestra implements MutableBean, Serializable {

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
    private String palestrante;

    private String codigoQrCode;


    public Palestra() {
    }

    public Palestra(long id, String nome, String descricao, String lugar, Programacao programacao, Date horaInicio, Date horaFim, Tipo tipo, String palestrante, String codigoQrCode) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.lugar = lugar;
        this.programacao = programacao;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.tipo = tipo;
        this.palestrante = palestrante;
        this.codigoQrCode = codigoQrCode;
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

    public String getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(String palestrante) {
        this.palestrante = palestrante;
    }

    public String getCodigoQrCode() {
        return codigoQrCode;
    }

    public void setCodigoQrCode(String codigoQrCode) {
        this.codigoQrCode = codigoQrCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Palestra palestra = (Palestra) o;

        if (id != palestra.id) return false;
        if (nome != null ? !nome.equals(palestra.nome) : palestra.nome != null) return false;
        if (descricao != null ? !descricao.equals(palestra.descricao) : palestra.descricao != null) return false;
        if (lugar != null ? !lugar.equals(palestra.lugar) : palestra.lugar != null) return false;
        if (programacao != null ? !programacao.equals(palestra.programacao) : palestra.programacao != null)
            return false;
        if (horaInicio != null ? !horaInicio.equals(palestra.horaInicio) : palestra.horaInicio != null) return false;
        if (horaFim != null ? !horaFim.equals(palestra.horaFim) : palestra.horaFim != null) return false;
        if (tipo != palestra.tipo) return false;
        if (palestrante != null ? !palestrante.equals(palestra.palestrante) : palestra.palestrante != null)
            return false;
        return codigoQrCode != null ? codigoQrCode.equals(palestra.codigoQrCode) : palestra.codigoQrCode == null;

    }

    @Override
    public int compareTo(@NonNull Object o) {
        if(o == null)
            return 0;
        Palestra palestra = (Palestra) o;
        if (getHoraInicio() == null || ((Palestra) o).getHoraInicio() == null)
            return 0;
        return getHoraInicio().compareTo(palestra.getHoraInicio());
    }
}