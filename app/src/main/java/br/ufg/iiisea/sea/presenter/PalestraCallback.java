package br.ufg.iiisea.sea.presenter;

import br.ufg.iiisea.sea.bean.Palestra;

/**
 * Created by w8.1 on 29/09/2016.
 */

public interface PalestraCallback {

    interface OnCheckInListener{
        void onErrorQRCode();
        void onErrorGeoLocation();
        void onError(int type, String msg);
        void onSucess(Palestra palestraAtualizada);
    }
}
