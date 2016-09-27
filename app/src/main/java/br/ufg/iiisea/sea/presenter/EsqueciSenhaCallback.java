package br.ufg.iiisea.sea.presenter;

/**
 * Created by fellipe on 21/09/16.
 */
public interface EsqueciSenhaCallback {
    interface OnEsqueciSenhaListener {
        void onSucess();
        void onLoad();
        void onErrorEmailInvalido();
        void onError(String error);
    }
}
