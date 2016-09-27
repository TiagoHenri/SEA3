package br.ufg.iiisea.sea.presenter;

/**
 * Created by fellipe on 20/09/16.
 */
public interface EntrarCallback {

    interface OnLoginListener {
        void onErrorCampoVazio();
        void onErrorTamanhoSenha();
        void onError(int type, String msg);
        void onSucess();
    }

    //exemplo:
    interface OnLogoutListner {
        void onSucess();
        void onError();
    }

}
