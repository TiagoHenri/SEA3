package br.ufg.iiisea.sea.interactor;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Palestrante;
import br.ufg.iiisea.sea.bean.Programacao;
import br.ufg.iiisea.sea.dao.PalestraDAO;
import br.ufg.iiisea.sea.dao.PalestranteDAO;
import br.ufg.iiisea.sea.dao.ProgramacaoDAO;
import br.ufg.iiisea.sea.model.PalestranteModel;
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
    private PalestranteDAO palestranteDAO;
    private ProgramacaoCallback.PaletrasListener paletrasListener;
    private PalestranteModel palestranteModel;

    public ProgramacaoInteractorImpl(Context context, Programacao programacaoAtual, ProgramacaoCallback.PaletrasListener paletrasListener) {
        this.programacaoAtual = programacaoAtual;
        this.paletrasListener = paletrasListener;
        this.palestraDAO = new PalestraDAO(context);
        this.palestranteDAO = new PalestranteDAO(context);
//        palestraDAO.deleteAll();
//        palestranteDAO.deleteAll();
    }

    @Override
    public List<Palestra> buscaPalestraInicio() {
        List<Palestra> palestrasSalvas = palestraDAO.getPalestrasPorProgramacao(programacaoAtual);
        return palestrasSalvas;
    }

    @Override
    public void atualizaPalestras() {
        palestranteModel.saveAllPalestrantesLinkedToProgramacaoId(programacaoAtual.getId());

        Backendless.Data.mapTableToClass("Palestra", Palestra.class);
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        String query = "programacao.id = "+programacaoAtual.getId();
        dataQuery.setWhereClause( query );

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addRelated( "programacao" );
        //queryOptions.addRelated("palestrante");
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

    @Override
    @Deprecated
    public void atualizaPalestras1() {

        final Map<Long, Palestra> mapaPalestras = new HashMap<>();

        Backendless.Data.mapTableToClass("Palestra", Palestra.class);
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        String query = "programacao.id = "+programacaoAtual.getId();
        dataQuery.setWhereClause( query );

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addRelated( "programacao" );
        dataQuery.setQueryOptions( queryOptions );
        Backendless.Data.of(Palestra.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Palestra>>() {
            @Override
            public void handleResponse(BackendlessCollection<Palestra> collection) {
                for(Iterator<Palestra> iterator = collection.getCurrentPage().iterator(); iterator.hasNext();) {
                    Palestra next = iterator.next();
                    if(next != null&& next.getId() > 0)
                        mapaPalestras.put(next.getId(), next);
                }
                atualizaPalestras2(mapaPalestras);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.e("handleFault", "Erro ao pegar todas as palestras do backendless: "+backendlessFault.toString());
            }
        });
    }

    @Deprecated
    private void atualizaPalestras2(final Map<Long, Palestra> mapaPalestra) {
        Backendless.Data.mapTableToClass("Palestra", Palestra.class);
        Backendless.Data.mapTableToClass("Palestrante", Palestrante.class);


        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        String query = "palestra.programacao.id = "+programacaoAtual.getId();
        dataQuery.setWhereClause( query );

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addRelated( "Palestrante" );
        queryOptions.addRelated( "Palestra" );
//        queryOptions.addRelated( "palestra.programacao" );
        dataQuery.setQueryOptions( queryOptions );

        final Map<Long, Palestrante> mapaPalestrante = new HashMap<>();


        Backendless.Data.of("tb_para_pate").find(dataQuery, new AsyncCallback<BackendlessCollection<Map>>() {
            @Override
            public void handleResponse(BackendlessCollection<Map> collection) {
//                for(Iterator<Map> iterator = collection.getCurrentPage().iterator();iterator.hasNext();) {
//                    Map nextMap = iterator.next();
//                    Palestra nextPalestra = (Palestra) nextMap.get("palestra");
//                    Palestrante nextPalestrante = (Palestrante) nextMap.get("palestrante");
//                    if(nextPalestrante != null && nextPalestrante.getId() > 0)
//                        mapaPalestrante.put(nextPalestrante.getId(), nextPalestrante);
//                    if(nextPalestra != null && nextPalestra.getId() > 0) {
//                        Palestra palestra = mapaPalestra.get(nextPalestra.getId());
//                        if(palestra != null && palestra.getId() > 0)
//                            palestra.addPalestrante(nextPalestrante);
//                        else {
//                            nextPalestra.addPalestrante(nextPalestrante);
//                            mapaPalestra.put(nextPalestra.getId(), nextPalestra);
//                        }
//                    }
//                }
//                Log.i("Palestra", Integer.toString(mapaPalestra.size()));
//                Log.i("Palestrante", Integer.toString(mapaPalestrante.size()));
//                atualizaPalestra3(mapaPalestra, mapaPalestrante);
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.e("handleFault", "Erro ao linkar os palestrantes com as palestras: " + backendlessFault.toString());
            }
        });

    }

    @Deprecated
    private void atualizaPalestra3(Map<Long, Palestra> mapaPalestra, Map<Long, Palestrante> mapaPalestrante) {


        final List<Palestra> listaPalestrasNovas = new ArrayList<>();
        final List<Palestra> listaPalestrasAtualizadas = new ArrayList<>();
        final List<Palestra> listaPalestrasDeletadas = new ArrayList<>();

        final List<Palestra> listaPalestrasSalvas = palestraDAO.getPalestrasPorProgramacao(programacaoAtual);
         //primeiro verifica as palestras
        for(Iterator<Palestra> iteratorPalestrasSalvas = listaPalestrasSalvas.iterator(); iteratorPalestrasSalvas.hasNext();) {
            Palestra palestraSalva = iteratorPalestrasSalvas.next();
            if(mapaPalestra.containsKey(palestraSalva.getId())) {
                Palestra palestraBkl = mapaPalestra.get(palestraSalva.getId());
                if(!palestraSalva.equals(palestraBkl)) { //palestra desatualizada
                    //atualiza
                    palestraDAO.edit(palestraBkl);
                    listaPalestrasAtualizadas.add(palestraBkl);
                }
                mapaPalestra.remove(palestraBkl.getId()); //remove do mapa palestras cuja o id esta salvo
            } else {
                //deleto do sql
                palestraDAO.delete(palestraSalva);
                listaPalestrasDeletadas.add(palestraSalva);
            }
        }

        for(Iterator<Long> iteratorKeyPalestrasBkl = mapaPalestra.keySet().iterator(); iteratorKeyPalestrasBkl.hasNext();) {
            Palestra palestraNova = mapaPalestra.get(iteratorKeyPalestrasBkl.next());
            palestraDAO.save(palestraNova);
            listaPalestrasNovas.add(palestraNova);
        }


        final List<Palestrante> palestrantesSalvo = palestranteDAO.getPalestrasPorProgramacao(programacaoAtual);

        for (Iterator<Palestrante> palestranteIterator = palestrantesSalvo.iterator(); palestranteIterator.hasNext();) {
            Palestrante palestranteSalvo = palestranteIterator.next();
            if(mapaPalestrante.containsKey(palestranteSalvo.getId())) {
                Palestrante palestranteBkl = mapaPalestrante.get(palestranteSalvo.getId());
                if(!palestranteBkl.equals(palestranteSalvo)) { // desatualizado
                    palestranteDAO.edit(palestranteBkl);
                }
                mapaPalestrante.remove(palestranteBkl);
            }  else {
                // deletar palestrante salvo
                palestranteDAO.delete(palestranteSalvo);
            }
        }

        for(Iterator<Long> iteratorKeyPalestrantes = mapaPalestrante.keySet().iterator(); iteratorKeyPalestrantes.hasNext();) {
            Palestrante palestranteNovo = mapaPalestrante.get(iteratorKeyPalestrantes.next());
            palestranteDAO.save(palestranteNovo);
        }





        if(listaPalestrasAtualizadas.isEmpty() && listaPalestrasDeletadas.isEmpty() && listaPalestrasNovas.isEmpty())
            paletrasListener.onNoPalestras();
        else {
            paletrasListener.onNewPalestras(listaPalestrasNovas);
            paletrasListener.onUpdatedPalestras(listaPalestrasAtualizadas);
            paletrasListener.onDeletedPalestras(listaPalestrasDeletadas);
        }
    }
}
