package br.ufg.iiisea.sea.interactor;

import android.content.Context;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Programacao;
import br.ufg.iiisea.sea.dao.ProgramacaoDAO;
import br.ufg.iiisea.sea.presenter.ProgramacaoPresenterImpl;

import java.util.List;

/**
 * Created by fellipe on 30/09/16.
 */
public class ProgramacaoInteractorImpl implements ProgramacaoInteractor {

    private Programacao programacaoAtual;
    private ProgramacaoDAO programacaoDAO;

    public ProgramacaoInteractorImpl(Context context, Programacao programacaoAtual) {
        this.programacaoAtual = programacaoAtual;
        this.programacaoDAO = new ProgramacaoDAO(context);
    }

    @Override
    public List<Palestra> getPalestraInicio() {
//        programacaoDAO.get
        return null;
    }
}
