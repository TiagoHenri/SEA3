package br.ufg.iiisea.sea.view;

/**
 * Created by fellipe on 04/10/16.
 */
public interface CheckinView {

    void checkinSucess();
    void qrCodeNaoValido();
    void usuarioChecked();
    void onError(String msg);
}
