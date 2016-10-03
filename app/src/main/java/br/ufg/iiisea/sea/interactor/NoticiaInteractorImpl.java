package br.ufg.iiisea.sea.interactor;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import br.ufg.iiisea.sea.presenter.NoticiaCallback;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Noticia;
import br.ufg.iiisea.sea.control.InitialConfig;
import br.ufg.iiisea.sea.dao.NoticiaDAO;
import br.ufg.iiisea.sea.presenter.HomeCallback;
import br.ufg.iiisea.sea.utils.ViewPagerAdapter;
import br.ufg.iiisea.sea.view.ListFragment;
import br.ufg.iiisea.sea.view.NoticiaFragment;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by fellipe on 23/09/16.
 */
public class NoticiaInteractorImpl implements NoticiaInteractor {

    private NoticiaDAO noticiaDAO;
    private NoticiaCallback callback;
    private Evento eventoAtual;

    public NoticiaInteractorImpl(Context context, NoticiaCallback noticiaCallback, Evento eventoAtual) {
        this.noticiaDAO = new NoticiaDAO(context);
        callback = noticiaCallback;
        this.eventoAtual = eventoAtual;

//        noticiaDAO.deleteAll();
    }

    @Override
    public List<Noticia> getNoticiasInicio() {

        List<Noticia> noticiasSalva= noticiaDAO.getAllNoticiaByEvento(eventoAtual);
        return noticiasSalva;
    }

    @Override
    public void atualizarNoticias() {
        Log.i("entrou", "atualizarNoticias");

        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss 'GMT'Z");
//        String dateMinimo;
//        Noticia last = noticiaDAO.getLastDateNoticia();
//        if(last!= null) {
//            dateMinimo = dateFormat.format(last.getData());
//        } else {
//            dateMinimo = dateFormat.format(new Date(0,0,0));
//        }
        Backendless.Data.mapTableToClass("Noticia", Noticia.class);
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        String query = "evento.id = "+eventoAtual.getId();
        dataQuery.setWhereClause( query );

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addRelated( "evento" );
//        queryOptions.setPageSize(20);
        dataQuery.setQueryOptions( queryOptions );
        final List<Noticia> noticiasSalva = noticiaDAO.getAllNoticiaByEvento(eventoAtual);
//        callback.noticiasAtualizadas(noticiasSalva);
        final Map<Long, Noticia> mapaNoticiasSalva = new HashMap<>(noticiasSalva.size());
        for(Iterator<Noticia> iterator = noticiasSalva.iterator(); iterator.hasNext();) {
            Noticia next = iterator.next();
            mapaNoticiasSalva.put(next.getId(), next);
        }
        final List<Noticia> noticiasNovas = new ArrayList<>();
        final List<Noticia> noticiasAtualizadas = new ArrayList<>();
        final List<Noticia> noticiasDeletadas = new ArrayList<>();
        Backendless.Data.of(Noticia.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Noticia>>() {
            @Override
            public void handleResponse(BackendlessCollection<Noticia> news) {
                Log.i("Backendless", "Fez busca por todas as noticias de um evento.");
                List<Noticia> noticiasNoBackendless = news.getCurrentPage();
                for(Iterator<Noticia> iterator = noticiasNoBackendless.iterator(); iterator.hasNext();) {
                    Noticia next = iterator.next();
                    if(mapaNoticiasSalva.containsKey(next.getId())) {
                        if(!mapaNoticiasSalva.get(next.getId()).equals(next)) {
                            noticiaDAO.edit(next);
                            noticiasAtualizadas.add(next);
                        }
                        mapaNoticiasSalva.remove(next.getId());
                    } else {
                        noticiaDAO.save(next);
                        noticiasNovas.add(next);
                    }
                }

                for(Iterator<Long> iteratorKey = mapaNoticiasSalva.keySet().iterator(); iteratorKey.hasNext();) {
                    Noticia next = mapaNoticiasSalva.get(iteratorKey.next());
                    noticiasDeletadas.add(next);
                    try {
                        noticiaDAO.delete(next);
                    } catch (SQLiteException e) {
                        Log.e("GRAVE", "nao esta deletando noticias do sql." + e.toString());
                    }
                }
                Log.i("noticias", "A: "+ Integer.toString(noticiasAtualizadas.size())
                        + " - D: " + Integer.toString(noticiasDeletadas.size())
                        + " - N: " + Integer.toString(noticiasNovas.size()));
                if(noticiasAtualizadas.isEmpty() && noticiasDeletadas.isEmpty() && noticiasNovas.isEmpty()) {
                    callback.naoExisteNoticiasNovas();
                } else {
                    Log.i("Entrou", "Entrou na secao dos callbacks");
                    callback.noticiasAtualizadas(noticiasAtualizadas);
                    callback.noticiasNovas(noticiasNovas);
                    callback.noticiasDeletadas(noticiasDeletadas);
                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                callback.noticiasNovas(noticiasSalva);
                Log.e("Erro no Bkl", "Erro ao buscar as noticias: "+backendlessFault.toString());
            }
        });




//
//        Backendless.Data.of(Noticia.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Noticia>>() {
//            @Override
//            public void handleResponse(BackendlessCollection<Noticia> news) {
//                Iterator<Noticia> iterator = news.getCurrentPage().iterator();
//
////                Log.i("BdN", "entrou"+Integer.toString(news.getTotalObjects()));
//                List<Noticia> lista = news.getCurrentPage();
//                List<Noticia> listaNovasNoticias = new ArrayList<Noticia>();
//                List<Noticia> listaNoticiasAtualizadas = new ArrayList<Noticia>();
//                if(lista.size() > 0) {
//                    for (int i = 0; i < lista.size(); i++) {
//                        try {
//                            Noticia not = lista.get(i);
////                            Log.i("not.id", Integer.toString((int) not.getId())
////                                    + " - " + dateFormat.format(not.getData())
////                                    + " - " + not.getTitulo());
//                            if (noticiaDAO.existeNoticiaPorId(not.getId())) {
////                                noticiaDAO.edit(not);
//
////                                Log.i("s", "0");
//                                listaNoticiasAtualizadas.add(not);
//                            } else {
////                                Log.i("s", "1");
////                                noticiaDAO.save(not);
//                                listaNovasNoticias.add(not);
//                            }
//                        } catch (SQLiteException ex) {
//                            Log.e("Tratamento de exceção:", ex.toString());
//                        }
//                    }
//                    if(!listaNovasNoticias.isEmpty())
//                        callback.noticiasNovas(listaNovasNoticias);
//                    if(!listaNoticiasAtualizadas.isEmpty())
//                        callback.noticiasAtualizadas(listaNoticiasAtualizadas);
//                } else {
//                    callback.naoExisteNoticiasNovas();
//                }
//            }
//
//            @Override
//            public void handleFault(BackendlessFault backendlessFault) {
//                Log.e("Erro no Bkl", "Erro ao buscar as noticias: "+backendlessFault.toString());
//            }
//        });
    }
}
