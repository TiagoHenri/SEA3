package br.ufg.iiisea.sea.presenter;

import android.support.v7.app.AppCompatActivity;
import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.control.InitialConfig;
import br.ufg.iiisea.sea.interactor.EntrarInteractor;
import br.ufg.iiisea.sea.interactor.EntrarInteractorImpl;
import br.ufg.iiisea.sea.utils.PresenterAbstract;
import br.ufg.iiisea.sea.view.*;

/**
 * Created by fellipe on 20/09/16.
 */
public class EntrarPresenterImpl extends PresenterAbstract implements EntrarPresenter, EntrarCallback.OnLoginListener {

    private EntrarInteractor interactor;
    private EntrarView view;

    public EntrarPresenterImpl() {
        this.interactor = new EntrarInteractorImpl();
    }

    public EntrarPresenterImpl(EntrarView view) {
        this();
        this.view = view;
    }

    @Override
    public void esqueceuSenha() {
        changeIntent((AppCompatActivity) view, EsqueciSenhaActivity.class);
    }

    @Override
    public void validaLogin(String email, String senha) {
        if(view != null) {
            view.showProgressLogin();
        }
        interactor.login(email, senha, this);
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
            changeIntent((AppCompatActivity) view, HomeActivity.class);

            view.finish();
        }
    }
}
