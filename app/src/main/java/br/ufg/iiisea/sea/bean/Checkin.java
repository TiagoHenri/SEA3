package br.ufg.iiisea.sea.bean;

import br.ufg.iiisea.sea.model.*;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;

import java.util.Date;

/**
 * Created by fellipe on 14/09/16.
 */
public class Checkin {

    private String usuarioId;
    private int palestraId;
    private Date horaEntrada;

    public Checkin() {
    }

    public Checkin(String usuarioId, int palestraId, Date horaEntrada) {
        this.usuarioId = usuarioId;
        this.palestraId = palestraId;
        this.horaEntrada = horaEntrada;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPalestraId() {
        return palestraId;
    }

    public void setPalestraId(int palestraId) {
        this.palestraId = palestraId;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
}
