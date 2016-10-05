package br.ufg.iiisea.sea.interactor;

import android.util.Log;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.presenter.PalestraCallback;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

/**
 * Created by w8.1 on 29/09/2016.
 */

public class PalestraInteractorImpl implements PalestraInteractor {

    private Palestra palestraDesatualizada;

    public PalestraInteractorImpl(Palestra palestraDesatualizada) {
        this.palestraDesatualizada = palestraDesatualizada;
    }

    @Override
    public void checkIn(final PalestraCallback.OnCheckInListener onCheckInFinishedObj) {
        Backendless.Data.mapTableToClass("Palestra", Palestra.class);
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        Log.e("Desatualizada", palestraDesatualizada.getNome() + palestraDesatualizada.getId());
        String query = "id = "+palestraDesatualizada.getId();
        dataQuery.setWhereClause( query );

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addRelated( "programacao" );
//        queryOptions.setPageSize(20);
        dataQuery.setQueryOptions( queryOptions );
        Backendless.Data.of(Palestra.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Palestra>>() {
            @Override
            public void handleResponse(BackendlessCollection<Palestra> collection) {
                if(!collection.getData().isEmpty()) {
                    Palestra palestra = collection.getData().get(0);

                    Log.e("Atualizada", palestra.getNome() + palestra.getId());
                    onCheckInFinishedObj.onSucess(palestra);
                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                onCheckInFinishedObj.onError(1, "Erro ao fazer Checkin. Verifique conexão");
                Log.e("Deu erro", "nao atualizou palestra para checkin.");
                // tramento de erro
            }
        });
    }
}
