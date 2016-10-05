package br.ufg.iiisea.sea.interactor;

import android.content.Context;
import android.database.sqlite.SQLiteException;
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

import java.util.*;

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
    public List<Palestra> buscaPalestraInicio() {
        List<Palestra> palestrasSalvas = palestraDAO.getPalestrasPorProgramacao(programacaoAtual);
        return palestrasSalvas;
    }

    @Override
    public void atualizaPalestras() {
        Backendless.Data.mapTableToClass("Palestra", Palestra.class);
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        String query = "programacao.id = "+programacaoAtual.getId();
        dataQuery.setWhereClause( query );

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addRelated( "programacao" );
//        queryOptions.setPageSize(20);
        dataQuery.setQueryOptions( queryOptions );
        final List<Palestra> palestrasSalvas = palestraDAO.getPalestrasPorProgramacao(programacaoAtual);
        final Map<Long, Palestra> mapaPalestrasSalvas = new HashMap<>(palestrasSalvas.size());
        for(Iterator<Palestra> iterator = palestrasSalvas.iterator(); iterator.hasNext();) {
            Palestra palestra = iterator.next();
            mapaPalestrasSalvas.put(palestra.getId(), palestra);
        }
        final List<Palestra> palestrasNovas = new ArrayList<>(palestrasSalvas.size());
        final List<Palestra> palestrasAtualizadas = new ArrayList<>(palestrasSalvas.size());
        final List<Palestra> palestrasDeletadas = new ArrayList<>(palestrasSalvas.size());

        Backendless.Data.of(Palestra.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Palestra>>() {
            @Override
            public void handleResponse(BackendlessCollection<Palestra> collection) {
                Log.i("Backendless", "Fez busca por todas as palestras de uma programacao.");
                List<Palestra> palestrasBackendless = collection.getCurrentPage();
                Log.i("mapa", "Tamanho" + mapaPalestrasSalvas.size());
                Log.i("bk", "Tamanho" + palestrasBackendless.size());
                for(Iterator<Palestra> iterator = palestrasBackendless.iterator(); iterator.hasNext();) {
                    Palestra next = iterator.next();
                    //Log.i("mapa", mapaPalestrasSalvas.get(next.getId()).toString());
                    Log.i("bk", next.toString());
                    if(mapaPalestrasSalvas.containsKey(next.getId())) {
                        if(!mapaPalestrasSalvas.get(next.getId()).equals(next)) {
                            palestraDAO.edit(next);
                            palestrasAtualizadas.add(next);
                        }
                        mapaPalestrasSalvas.remove(next.getId());
                    } else {
                        palestraDAO.save(next);
                        palestrasNovas.add(next);
                    }
                }
                for(Iterator<Long> iteratorKey = mapaPalestrasSalvas.keySet().iterator(); iteratorKey.hasNext();) {
                    Palestra next = mapaPalestrasSalvas.get(iteratorKey.next());
                    palestrasDeletadas.add(next);
                    try {
                        palestraDAO.delete(next);
                    } catch (SQLiteException e) {
                        Log.e("GRAVE", "nao esta deletando palestra do sql." + e.toString());
                    }
                }
                Log.i("palestras", "A: "+ Integer.toString(palestrasAtualizadas.size())
                        + " - D: " + Integer.toString(palestrasDeletadas.size())
                        + " - N: " + Integer.toString(palestrasNovas.size()));
                if(palestrasAtualizadas.isEmpty() && palestrasDeletadas.isEmpty() && palestrasNovas.isEmpty()) {
                    paletrasListener.onNoPalestras();
                } else {
                    paletrasListener.onNewPalestras(palestrasNovas);
                    paletrasListener.onUpdatedPalestras(palestrasAtualizadas);
                    paletrasListener.onDeletedPalestras(palestrasDeletadas);
                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                paletrasListener.onNewPalestras(palestrasSalvas);
                Log.e("Erro no Bkl", "Erro ao buscar as palestras: "+backendlessFault.toString());
            }
        });
    }

    //
//    @Override
//    public void buscaPalestraInicio() {
//        final List<Palestra> palestrasSalva = palestraDAO.getPalestrasPorProgramacao(programacaoAtual);
//        if(palestrasSalva.isEmpty()) {
//
//            Backendless.Data.mapTableToClass("Palestra", Palestra.class);
//            BackendlessDataQuery dataQuery = new BackendlessDataQuery();
//
//            String query = "programacao.id = " + programacaoAtual.getId();
//            dataQuery.setWhereClause(query);
//
//            QueryOptions queryOptions = new QueryOptions();
//            queryOptions.addRelated("programacao");
//            dataQuery.setQueryOptions( queryOptions );
//            Backendless.Data.of(Palestra.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Palestra>>() {
//                @Override
//                public void handleResponse(BackendlessCollection<Palestra> collection) {
//                    Log.i("Backendless", "Fez busca por todas as palestras de um evento.");
//                    Iterator<Palestra> iterator = collection.getCurrentPage().iterator();
//                    while (iterator.hasNext()) {
//                        Palestra next = iterator.next();
//                        // aqui entra a parte dos palestrantes
//                        palestraDAO.save(next);
//                        palestrasSalva.add(next);
//                    }
//                    paletrasListener.onSavedPalestras(palestrasSalva);
//                }
//
//                @Override
//                public void handleFault(BackendlessFault backendlessFault) {
//                    Log.e("backendless", "problema com as palestras: "+backendlessFault.toString());
//                }
//            });
//        } else {
//            paletrasListener.onSavedPalestras(palestrasSalva);
//        }
//    }
}
