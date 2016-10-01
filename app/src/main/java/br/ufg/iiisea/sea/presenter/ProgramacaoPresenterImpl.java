package br.ufg.iiisea.sea.presenter;

import android.content.Context;
import android.util.Log;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Programacao;
import br.ufg.iiisea.sea.interactor.ProgramacaoInteractor;
import br.ufg.iiisea.sea.interactor.ProgramacaoInteractorImpl;
import br.ufg.iiisea.sea.view.ProgramacaoView;

import java.util.List;

/**
 * Created by fellipe on 30/09/16.
 */
public class ProgramacaoPresenterImpl implements ProgramacaoPresenter, ProgramacaoCallback.PaletrasListener {

    private Programacao programacaoAtual;
    private ProgramacaoInteractor interactor;
    private ProgramacaoView view;

    public ProgramacaoPresenterImpl(ProgramacaoView view, Context context, Programacao programacaoAtual) {
        this.view = view;
        this.programacaoAtual = programacaoAtual;
        this.interactor = new ProgramacaoInteractorImpl(context, programacaoAtual, this);
    }

    // acesso para view

    @Override
    public void preparaPaletrasInicial() {
        interactor.buscaPalestraInicio();
    }

    @Override
    public void abrirDescricao(Palestra palestra) {

    }

    @Override
    public void atualizarPalestras() {

    }

    //callback

    @Override
    public void onSavedPalestras(List<Palestra> palestrasNovas) {
        if(!palestrasNovas.isEmpty()) {
            view.addPalestra(palestrasNovas);
        }
    }


    @Override
    public void onUpdatedPalestras(List<Palestra> palestrasModificadas) {

    }

    @Override
    public void onError(String msg) {

    }
}
