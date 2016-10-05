package br.ufg.iiisea.sea.model;

import android.content.Context;
import android.util.Log;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Palestrante;
import br.ufg.iiisea.sea.dao.PalestraDAO;
import br.ufg.iiisea.sea.dao.PalestranteDAO;
import br.ufg.iiisea.sea.utils.AbstractModel;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by fellipe on 03/10/16.
 */
public class PalestranteModel extends AbstractModel {

    private PalestranteDAO dao;

    public PalestranteModel(Context context) {
        super(context);
        this.dao = new PalestranteDAO(context);
    }

    public void aa() {
        Backendless.Data.mapTableToClass("Palestrante", Palestrante.class);
        Backendless.Data.of(Palestrante.class).find(new AsyncCallback<BackendlessCollection<Palestrante>>() {
            @Override
            public void handleResponse(BackendlessCollection<Palestrante> collection) {
                Palestrante p = collection.getCurrentPage().get(0);
                Log.i("aa", p.toString());
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        });
    }

    public void saveAllPalestrantesLinkedToProgramacaoId(long programacaoId) {
        Backendless.Data.mapTableToClass("Palestrante", Palestrante.class);
        Backendless.Data.mapTableToClass("Palestra", Palestra.class);

        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
//        String query = "palestra.programacao.id = "+programacaoId;
//        Log.i("sucess", query);
//        dataQuery.setWhereClause( query );

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addRelated( "Palestrante" );
        queryOptions.addRelated( "Palestra" );
        //queryOptions.addRelated( "palestra.programacao" );
//        queryOptions.setPageSize(20);
        dataQuery.setQueryOptions( queryOptions );

        Backendless.Data.of("tb_para_pate").find(dataQuery, new AsyncCallback<BackendlessCollection<Map>>() {
            @Override
            public void handleResponse(BackendlessCollection<Map> collection) {
                Log.i("sucess", Integer.toString(collection.getCurrentPage().size()));
                Log.i("sucess", collection.getCurrentPage().toString());

                for(Iterator<Map> iterator = collection.getCurrentPage().iterator(); iterator.hasNext(); ) {
                    Map next = iterator.next();
                    Palestrante palestrante = (Palestrante) next.get("palestrante");
                    Palestra palestra = (Palestra) next.get("palestra");
                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        });
    }
}
