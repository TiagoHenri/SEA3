package br.ufg.iiisea.sea.presenter;

import android.content.Context;
import android.util.Log;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Noticia;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Programacao;
import br.ufg.iiisea.sea.interactor.ProgramacaoInteractor;
import br.ufg.iiisea.sea.interactor.ProgramacaoInteractorImpl;
import br.ufg.iiisea.sea.view.ProgramacaoView;

import java.util.Iterator;
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

        List<Palestra> palestrasSalvas = interactor.buscaPalestraInicio();
        if (!palestrasSalvas.isEmpty())
            view.addPalestra(palestrasSalvas);
    }

    @Override
    public void abrirDescricao(Palestra palestra) {

    }

    @Override
    public void atualizarPalestras() {
        interactor.atualizaPalestras();
    }

    // callback


    @Override
    public void onNewPalestras(List<Palestra> palestrasNovas) {
        if(palestrasNovas == null){
            view.concluidoBusca();
        } else {
            if (!palestrasNovas.isEmpty())
                view.addPalestra(palestrasNovas);
            view.concluidoBusca();
        }
    }

    @Override
    public void onUpdatedPalestras(List<Palestra> palestrasModificadas) {
        if(!palestrasModificadas.isEmpty()) {
            for(Iterator<Palestra> iterator = palestrasModificadas.iterator(); iterator.hasNext();)
                view.removePalestra(iterator.next());
            view.addPalestra(palestrasModificadas);
        }
        view.concluidoBusca();
    }

    @Override
    public void onDeletedPalestras(List<Palestra> palestrasDeletadas) {
        if(!palestrasDeletadas.isEmpty()) {
            for (Iterator<Palestra> iterator = palestrasDeletadas.iterator(); iterator.hasNext(); )
                view.removePalestra(iterator.next());
        }
        view.concluidoBusca();
    }

    @Override
    public void onNoPalestras() {
        view.showNenhumaPalestraDialog();
        view.concluidoBusca();
    }

    @Override
    public void onError(String msg) {
        view.showToastMessage(msg);
    }
}
