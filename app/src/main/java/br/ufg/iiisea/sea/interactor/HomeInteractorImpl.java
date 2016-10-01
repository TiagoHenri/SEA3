package br.ufg.iiisea.sea.interactor;

import android.content.Context;
import android.util.Log;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Programacao;
import br.ufg.iiisea.sea.dao.EventoDAO;
import br.ufg.iiisea.sea.dao.ProgramacaoDAO;
import br.ufg.iiisea.sea.presenter.HomeCallback;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fellipe on 27/09/16.
 */
public class HomeInteractorImpl implements HomeInteractor {

    private EventoDAO eventoDAO;
    private ProgramacaoDAO programacaoDAO;
    private HomeCallback homeCallback;

    public HomeInteractorImpl(Context context, HomeCallback homeCallback) {
        eventoDAO = new EventoDAO(context);
        programacaoDAO = new ProgramacaoDAO(context);
        this.homeCallback = homeCallback;

        //eventoDAO.deleteAll();
    }

    @Override
    public void preparaDadosIniciais() {
        Evento eventoAtual = eventoDAO.getEventoSalvo();
        Evento aux =  new Evento(1, "Simpósio de Empreendedorismo Ambiental", "", null, null);
        boolean hasErrorEvento = false;
        if (eventoAtual == null) {
            if(eventoDAO.save(aux) < 0) {
                Log.e("Erro", "nao foi salvo");
                hasErrorEvento = true;
                homeCallback.houveErro("Não há evento cadastrado!");
            }
        } else {
            aux = eventoAtual;
        }
        if(!hasErrorEvento) {
            //
            setProgramacaoInicial(aux);
//            homeCallback.dadosIniciaisEncontrado(aux, getProgramacaoInicial(aux));
        }
    }

    private void setProgramacaoInicial(final Evento evento) {

        final List<Programacao> programacaoList = programacaoDAO.getProgramacaoByEvento(evento);
        if(!programacaoList.isEmpty()) {
            homeCallback.dadosIniciaisEncontrado(evento, programacaoList);
        } else {
            Log.i("getProgramacaoInicial", "Nao ha programacao salva no sqlite");
            Backendless.Data.mapTableToClass("Programacao", Programacao.class);
            BackendlessDataQuery dataQuery = new BackendlessDataQuery();
            String query = "evento.id = " + evento.getId();

            dataQuery.setWhereClause( query );

            QueryOptions queryOptions = new QueryOptions();
            queryOptions.addRelated("evento");
            dataQuery.setQueryOptions( queryOptions );
            Backendless.Data.of(Programacao.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Programacao>>() {
                @Override
                public void handleResponse(BackendlessCollection<Programacao> collection) {
                    Iterator<Programacao> iterator = collection.getCurrentPage().iterator();
                    while(iterator.hasNext()) {
                        Programacao prog = iterator.next();
                        programacaoDAO.save(prog);
                        programacaoList.add(prog);
                    }
                    Log.i("getProgramacaoInicial", "Buscou do backendless " + Integer.toString(programacaoList.size()) + " programacoes.");
                    homeCallback.dadosIniciaisEncontrado(evento, programacaoList);
                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {

                    Log.e("Erro", "backendless errou "+backendlessFault.toString());
                    homeCallback.houveErro("Não há programação cadastrada!");
                    homeCallback.dadosIniciaisEncontrado(evento, null);
                }
            });
        }
    }
}
