package br.ufg.iiisea.sea.presenter;

import android.content.Context;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Programacao;
import br.ufg.iiisea.sea.interactor.ProgramacaoInteractor;
import br.ufg.iiisea.sea.interactor.ProgramacaoInteractorImpl;
import br.ufg.iiisea.sea.view.ProgramacaoView;

/**
 * Created by fellipe on 30/09/16.
 */
public class ProgramacaoPresenterImpl implements ProgramacaoPresenter {

    private Programacao programacaoAtual;
    private ProgramacaoInteractor interactor;
    private ProgramacaoView view;

    public ProgramacaoPresenterImpl(ProgramacaoView view, Context context, Programacao programacaoAtual) {
        this.view = view;
        this.programacaoAtual = programacaoAtual;
        this.interactor = new ProgramacaoInteractorImpl(context, programacaoAtual);
    }

    @Override
    public void preparaPaletrasInicial() {

    }

    @Override
    public void abrirDescricao(Palestra palestra) {

    }

    @Override
    public void atualizarPalestras() {

    }
}
