package br.ufg.iiisea.sea.presenter;

import android.content.Context;
import android.util.Log;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Noticia;
import br.ufg.iiisea.sea.interactor.NoticiaInteractor;
import br.ufg.iiisea.sea.interactor.NoticiaInteractorImpl;
import br.ufg.iiisea.sea.utils.PresenterAbstract;
import br.ufg.iiisea.sea.view.NoticiaView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fellipe on 23/09/16.
 */
public class NoticiaPresenterImpl extends PresenterAbstract implements NoticiaPresenter, NoticiaCallback {

    private NoticiaView view;
    private NoticiaInteractor interactor;
    private Evento eventoAtual;

    public NoticiaPresenterImpl(NoticiaView view, Context context, Evento eventoAtual) {
        this.view = view;
        this.eventoAtual = eventoAtual;
        this.interactor = new NoticiaInteractorImpl(context, this, eventoAtual);
    }

    @Override
    public void preparaNoticiasInicial() {
        List<Noticia> list = interactor.getNoticiasInicio();
        Log.i("np", "pNI");
        if(list.isEmpty()) {
            view.showNenhumaNoticiaMessage();
        } else {
            view.addNoticia(list);
        }
    }

    @Override
    public void atualizarNoticias() {
        interactor.atualizarNoticias();
    }

    @Override
    public void noticiasNovas(List<Noticia> novasNoticias) {
        view.addNoticia(novasNoticias);
        view.concluidoAtualizacao();
    }

    @Override
    public void noticiasAtualizadas(List<Noticia> noticiasAtualizadas) {
        for(int i = 0; i < noticiasAtualizadas.size();i++)
            view.removeNoticia(noticiasAtualizadas.get(i));
        view.addNoticia(noticiasAtualizadas);
        view.concluidoAtualizacao();
    }

    @Override
    public void naoExisteNoticiasNovas() {
        view.showNenhumaNoticiaMessage();
        view.concluidoAtualizacao();
    }

    @Override
    public void onError(String msg) {

    }
}
