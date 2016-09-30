package br.ufg.iiisea.sea.presenter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Programacao;
import br.ufg.iiisea.sea.control.InitialConfig;
import br.ufg.iiisea.sea.interactor.*;
import br.ufg.iiisea.sea.utils.PresenterAbstract;
import br.ufg.iiisea.sea.utils.ViewPagerAdapter;
import br.ufg.iiisea.sea.view.HomeActivity;
import br.ufg.iiisea.sea.view.HomeView;
import br.ufg.iiisea.sea.view.ListFragment;
import br.ufg.iiisea.sea.view.NoticiaFragment;

import java.util.List;

/**
 * Created by tiago on 25/09/16.
 */
public class HomePresenterImpl extends PresenterAbstract implements HomePresenter, HomeCallback {

//    private NoticiaInteractor noticiaInteractor;
    //private ProgramacaoInteractor programacaoInteractor;
    private HomeView view;
    private Context context;
    private HomeInteractor interactor;
   // private ViewPagerAdapter adapter;

    public HomePresenterImpl(HomeView view, Context context) {
        this.view = view;
        interactor = new HomeInteractorImpl(context, this);
        configuraTabs();
    }

//    public HomePresenterImpl(HomeView view, Context context) {
//        this(context, view);
//        this.view = view;
//        this.context = context;
//       // this.adapter = adapter;
//    }

    @Override
    public void configuraTabs() {
        interactor.preparaDadosIniciais();
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void dadosIniciaisEncontrado(Evento evento, List<Programacao> programacaoList) {

//        for(int i = 0; i < programacaoList.size(); i++) {
//            view.addFragmento(ProgramacaoFragment(programacaoList.get(i)), "Programacao dia "+programacaoList.get(i).);
//        }
        Log.i("Evento", evento.getNome());
        view.addFragmento(NoticiaFragment.newInstance(evento), "Notícias");

    }

    @Override
    public void houveErro(String msg) {
        view.showToastByString(msg);
    }
}
