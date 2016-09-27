package br.ufg.iiisea.sea.presenter;

/**
 * Created by tiago on 24/09/16.
 */
public interface RegistrarPresenter extends EntrarCallback {

    void validaRegistro(String nome, String email, String senha);
    void onDestroy();
}
