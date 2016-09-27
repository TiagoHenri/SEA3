package br.ufg.iiisea.sea.presenter;

import android.content.Context;
import br.ufg.iiisea.sea.bean.Noticia;
import br.ufg.iiisea.sea.interactor.NoticiaInteractor;
import br.ufg.iiisea.sea.interactor.NoticiaInteractorImpl;
import br.ufg.iiisea.sea.utils.PresenterAbstract;
import br.ufg.iiisea.sea.view.NoticiaView;

import java.util.List;

/**
 * Created by fellipe on 23/09/16.
 */
public class NoticiaPresenterImpl extends PresenterAbstract implements NoticiaPresenter {

    private NoticiaView view;
    private NoticiaInteractor interactor;

    public NoticiaPresenterImpl(NoticiaView view, Context context) {
        this.view = view;
        this.interactor = new NoticiaInteractorImpl(context);
    }

    @Override
    public List<Noticia> preparaNoticiasInicial() {
        List<Noticia> list = interactor.getNoticiasInicio();
        if(list.isEmpty()) {
            view.showNenhumaNoticiaMessage();
            return null;
        }
        return list;
    }

    @Override
    public List<Noticia> atualizarNoticias() {
        return null;
    }
}
