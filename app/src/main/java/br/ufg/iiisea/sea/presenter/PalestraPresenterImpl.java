package br.ufg.iiisea.sea.presenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.interactor.PalestraInteractor;
import br.ufg.iiisea.sea.interactor.PalestraInteractorImpl;
import br.ufg.iiisea.sea.utils.PresenterAbstract;
import br.ufg.iiisea.sea.view.HomeActivity;
import br.ufg.iiisea.sea.view.PalestraView;

/**
 * Created by w8.1 on 29/09/2016.
 */

public class PalestraPresenterImpl extends PresenterAbstract implements PalestraPresenter, PalestraCallback.OnCheckInListener {

    private PalestraInteractor interactor;
    private PalestraView view;
    private Palestra palestraAtual;

    public PalestraPresenterImpl(Palestra palestra){
        this.palestraAtual = palestra;
        this.interactor = new PalestraInteractorImpl(palestra);
    }

    public PalestraPresenterImpl(PalestraView view, Palestra palestra){
        this(palestra);
        this.view = view;
    }

    @Override
    public void checkIn() {
        view.showProgressCheckIn();
        interactor.checkIn(this);
    }

    @Override
    public void onErrorQRCode() {
        if(view != null) {
            view.hideProgress();
            view.showToastMessage(R.string.erroQRCode);
        }
    }

    @Override
    public void onErrorGeoLocation() {
        if(view != null) {
            view.hideProgress();
            view.showToastMessage(R.string.erroGeoLocation);
        }
    }

    @Override
    public void onError(int type, String msg) {
        if(view != null) {
            view.hideProgress();
            view.showToastByString(msg);
        }
    }

    @Override
    public void onSucess(Palestra palestraAtualizada) {
        if(view != null) {
            view.hideProgress();

            view.chamaCheckinActivity(palestraAtualizada);
            view.finish();
        }
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
