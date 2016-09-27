package br.ufg.iiisea.sea.interactor;

import br.ufg.iiisea.sea.presenter.EntrarCallback;

/**
 * Created by fellipe on 20/09/16.
 */
public interface EntrarInteractor {

    void login(final String email, final String senha, final EntrarCallback.OnLoginListener onLoginFinishedObj);
}
