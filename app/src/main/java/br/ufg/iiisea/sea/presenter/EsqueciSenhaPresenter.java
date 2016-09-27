package br.ufg.iiisea.sea.presenter;

/**
 * Created by fellipe on 21/09/16.
 */
public interface EsqueciSenhaPresenter extends EsqueciSenhaCallback {
    void validaEsqueciSenha(String email);
    void onDestroy();
}
