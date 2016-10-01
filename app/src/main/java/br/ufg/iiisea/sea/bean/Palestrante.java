package br.ufg.iiisea.sea.bean;

import android.support.annotation.NonNull;
import br.ufg.iiisea.sea.utils.MutableBean;

import java.util.ArrayList;

/**
 * Created by fellipe on 14/09/16.
 */
public class Palestrante implements MutableBean {

    private long id;
    private String nome;
    private String biografia;

    public Palestrante() {

    }

    public Palestrante(long id, String nome, String biografia) {
        this.id = id;
        this.nome = nome;
        this.biografia = biografia;
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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
