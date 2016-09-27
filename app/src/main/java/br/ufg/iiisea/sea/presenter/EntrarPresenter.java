package br.ufg.iiisea.sea.presenter;

/**
 * Created by fellipe on 20/09/16.
 */
public interface EntrarPresenter extends EntrarCallback {

    void esqueceuSenha();
    void validaLogin(String email, String senha);
    void onDestroy();
}
