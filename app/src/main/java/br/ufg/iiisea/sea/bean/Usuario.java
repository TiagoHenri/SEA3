package br.ufg.iiisea.sea.bean;

import br.ufg.iiisea.sea.utils.MutableBean;

import java.util.ArrayList;

/**
 * Created by fellipe on 14/09/16.
 */
public class Usuario implements MutableBean{

    private long id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
