package br.ufg.iiisea.sea.interactor;

import br.ufg.iiisea.sea.presenter.PalestraCallback;

/**
 * Created by w8.1 on 29/09/2016.
 */

public interface PalestraInteractor {

    void checkIn(final PalestraCallback.OnCheckInListener onCheckInFinishedObj);
}
