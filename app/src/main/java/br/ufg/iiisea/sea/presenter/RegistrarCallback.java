package br.ufg.iiisea.sea.presenter;

/**
 * Created by tiago on 24/09/16.
 */
public interface RegistrarCallback {

    interface OnRegistroListener {
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
