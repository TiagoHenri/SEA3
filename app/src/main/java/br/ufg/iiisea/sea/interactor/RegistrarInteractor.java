package br.ufg.iiisea.sea.interactor;

import br.ufg.iiisea.sea.presenter.EntrarCallback;
import br.ufg.iiisea.sea.presenter.RegistrarCallback;

/**
 * Created by tiago on 24/09/16.
 */
public interface RegistrarInteractor {

    void registrar(final String nome, final String email, final String senha, final RegistrarCallback.OnRegistroListener onRegistroFinishedObj);
}
