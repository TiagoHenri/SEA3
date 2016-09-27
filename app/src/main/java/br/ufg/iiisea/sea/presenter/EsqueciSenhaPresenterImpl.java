package br.ufg.iiisea.sea.presenter;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.interactor.EsqueciSenhaInteractor;
import br.ufg.iiisea.sea.interactor.EsqueciSenhaInteractorImpl;
import br.ufg.iiisea.sea.utils.PresenterAbstract;
import br.ufg.iiisea.sea.view.EsqueciSenhaView;

/**
 * Created by fellipe on 21/09/16.
 */
public class EsqueciSenhaPresenterImpl extends PresenterAbstract implements EsqueciSenhaPresenter, EsqueciSenhaCallback.OnEsqueciSenhaListener {

    private EsqueciSenhaInteractor interactor;
    private EsqueciSenhaView view;

    public EsqueciSenhaPresenterImpl() {
        interactor = new EsqueciSenhaInteractorImpl(this);
    }

    public EsqueciSenhaPresenterImpl(EsqueciSenhaView view) {
        this();
        this.view = view;
    }

    @Override
    public void validaEsqueciSenha(String email) {
       interactor.realizaEsqueciSenha(email);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onSucess() {
        view.hideProgress();
        view.showToastMessage(R.string.msgLinkEnviado);
        view.finish();
    }

    @Override
    public void onLoad() {
        view.showProgress();
    }

    @Override
    public void onErrorEmailInvalido() {
        view.hideProgress();
        view.showToastMessage(R.string.erroCampoVazio);
    }

    @Override
    public void onError(String error) {
        view.showToastByString(error);
        view.hideProgress();
    }
}
