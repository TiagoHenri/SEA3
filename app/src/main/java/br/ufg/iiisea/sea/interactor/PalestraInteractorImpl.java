package br.ufg.iiisea.sea.interactor;

import br.ufg.iiisea.sea.presenter.PalestraCallback;

/**
 * Created by w8.1 on 29/09/2016.
 */

public class PalestraInteractorImpl implements PalestraInteractor {
    @Override
    public void checkIn(PalestraCallback.OnCheckInListener onCheckInFinishedObj) {
        if(true){
            onCheckInFinishedObj.onSucess();
        } else {
            onCheckInFinishedObj.onError(1, "Erro");
        }
    }
}
