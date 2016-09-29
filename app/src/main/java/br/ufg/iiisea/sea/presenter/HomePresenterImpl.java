package br.ufg.iiisea.sea.presenter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.control.InitialConfig;
import br.ufg.iiisea.sea.interactor.EntrarInteractor;
import br.ufg.iiisea.sea.interactor.EntrarInteractorImpl;
import br.ufg.iiisea.sea.interactor.NoticiaInteractor;
import br.ufg.iiisea.sea.interactor.NoticiaInteractorImpl;
import br.ufg.iiisea.sea.utils.PresenterAbstract;
import br.ufg.iiisea.sea.utils.ViewPagerAdapter;
import br.ufg.iiisea.sea.view.HomeView;
import br.ufg.iiisea.sea.view.ListFragment;
import br.ufg.iiisea.sea.view.NoticiaFragment;
import br.ufg.iiisea.sea.view.NoticiaView;

/**
 * Created by tiago on 25/09/16.
 */
public class HomePresenterImpl extends PresenterAbstract implements HomePresenter, HomeCallback.OnLoadingListener {

    private NoticiaInteractor noticiaInteractor;
    //private ProgramacaoInteractor programacaoInteractor;
    private HomeView view;
    private Context context;

    private NoticiaView notView;

    public HomePresenterImpl(Context context) {
        this.notView = new NoticiaFragment();
        this.noticiaInteractor = new NoticiaInteractorImpl(context);
        //this.programacaoInteractor = new ProgramacaoInteractor();
    }

    public HomePresenterImpl(HomeView view, Context context) {
        this(context);
        this.view = view;
        this.context = context;
        //this.adapter = adapter;
    }

    @Override
    public void configuraTabs() {
        view.showProgressLoading();
        noticiaInteractor.atualizarNoticias(this);
    }

    @Override
    public void onDestroy() {
        view = null;
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

            adapter.getItem(0).getView()
            adapter.notifyDataSetChanged();

            view.showToastMessage(R.string.sucesso);
        }
    }
}
