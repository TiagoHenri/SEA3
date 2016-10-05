package br.ufg.iiisea.sea.bean;

import br.ufg.iiisea.sea.model.*;

import java.util.Date;

/**
 * Created by fellipe on 14/09/16.
 */
public class CheckIn {

    private Usuario usuario;
    private Palestra palestra;
    private Date horaEntrada;
    @Deprecated
    private Date horaSaida;

    public CheckIn() {
    }

    public CheckIn(Usuario usuario, Palestra palestra, Date horaEntrada, Date horaSaida) {
        this.usuario = usuario;
        this.palestra = palestra;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Palestra getPalestra() {
        return palestra;
    }

    public void setPalestra(Palestra palestra) {
        this.palestra = palestra;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Date horaSaida) {
        this.horaSaida = horaSaida;
    }
}
