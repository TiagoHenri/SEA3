package br.ufg.iiisea.sea.interactor;

import android.content.Context;
import android.util.Log;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Programacao;
import br.ufg.iiisea.sea.dao.PalestraDAO;
import br.ufg.iiisea.sea.dao.ProgramacaoDAO;
import br.ufg.iiisea.sea.presenter.ProgramacaoCallback;
import br.ufg.iiisea.sea.presenter.ProgramacaoPresenterImpl;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.Iterator;
import java.util.List;

/**
 * Created by fellipe on 30/09/16.
 */
public class ProgramacaoInteractorImpl implements ProgramacaoInteractor {

    private Programacao programacaoAtual;
    private PalestraDAO palestraDAO;
    private ProgramacaoCallback.PaletrasListener paletrasListener;

    public ProgramacaoInteractorImpl(Context context, Programacao programacaoAtual, ProgramacaoCallback.PaletrasListener paletrasListener) {
        this.programacaoAtual = programacaoAtual;
        this.paletrasListener = paletrasListener;
        this.palestraDAO = new PalestraDAO(context);
//        palestraDAO.deleteAll();
    }

    @Override
    public void buscaPalestraInicio() {
        final List<Palestra> palestrasSalva = palestraDAO.getPalestrasPorProgramacao(programacaoAtual);
        if(palestrasSalva.isEmpty()) {

            Backendless.Data.mapTableToClass("Palestra", Palestra.class);
            BackendlessDataQuery dataQuery = new BackendlessDataQuery();

            String query = "programacao.id = " + programacaoAtual.getId();
            dataQuery.setWhereClause(query);

            QueryOptions queryOptions = new QueryOptions();
            queryOptions.addRelated("programacao");
            dataQuery.setQueryOptions( queryOptions );
            Backendless.Data.of(Palestra.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Palestra>>() {
                @Override
                public void handleResponse(BackendlessCollection<Palestra> collection) {
                    Log.i("Backendless", "Fez busca por todas as palestras de um evento.");
                    Iterator<Palestra> iterator = collection.getCurrentPage().iterator();
                    while (iterator.hasNext()) {
                        Palestra next = iterator.next();
                        // aqui entra a parte dos palestrantes
                        palestraDAO.save(next);
                        palestrasSalva.add(next);
                    }
                    paletrasListener.onSavedPalestras(palestrasSalva);
                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {
                    Log.e("backendless", "problema com as palestras: "+backendlessFault.toString());
                }
            });
        } else {
            paletrasListener.onSavedPalestras(palestrasSalva);
        }
    }
}
