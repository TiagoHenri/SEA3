package br.ufg.iiisea.sea.presenter;

import android.support.v7.app.AppCompatActivity;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.interactor.EntrarInteractor;
import br.ufg.iiisea.sea.interactor.EntrarInteractorImpl;
import br.ufg.iiisea.sea.interactor.RegistrarInteractor;
import br.ufg.iiisea.sea.interactor.RegistrarInteractorImpl;
import br.ufg.iiisea.sea.utils.PresenterAbstract;
import br.ufg.iiisea.sea.view.RegistrarView;

/**
 * Created by tiago on 24/09/16.
 */
public class RegistrarPresenterImpl extends PresenterAbstract implements RegistrarPresenter, RegistrarCallback.OnRegistroListener {

    private RegistrarInteractor interactor;
    private RegistrarView view;

    public RegistrarPresenterImpl() {
        this.interactor = new RegistrarInteractorImpl();
    }

    public RegistrarPresenterImpl(RegistrarView view) {
        this();
        this.view = view;
    }

    @Override
    public void validaRegistro(String nome, String email, String senha) {
        if(view != null) {
            view.showProgressRegistro();
        }
        interactor.registrar(nome, email, senha, this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onErrorCampoVazio() {
        if(view != null) {
            view.hideProgress();
            view.showToastMessage(R.string.erroCampoVazio);
        }
    }

    @Override
    public void onErrorTamanhoSenha() {
        view.hideProgress();
        view.showToastMessage(R.string.erroTamanhoSenha);
    }

    @Override
    public void onError(int type, String msg) {
        if(view != null) {
            view.hideProgress();
            view.showToastByString(msg);
        }
    }

    @Override
    public void onSucess() {
        if(view != null) {
            view.hideProgress();

            view.showToastMessage(R.string.sucessoRegistro);
            view.finish();
        }
    }
}
