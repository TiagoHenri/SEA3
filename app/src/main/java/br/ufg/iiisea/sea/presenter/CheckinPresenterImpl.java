package br.ufg.iiisea.sea.presenter;

import android.util.Log;
import br.ufg.iiisea.sea.bean.CheckIn;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Usuario;
import br.ufg.iiisea.sea.control.InitialConfig;
import br.ufg.iiisea.sea.view.CheckinView;
import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fellipe on 04/10/16.
 */
public class CheckinPresenterImpl implements CheckinPresenter {

    private Palestra palestraAtual;
    private CheckinView view;

    public CheckinPresenterImpl(Palestra palestraAtual, CheckinView view) {
        this.palestraAtual = palestraAtual;
        this.view = view;
    }


    private void salvaCheckinNoBkl(CheckIn novoCheckin) {
        Backendless.Persistence.of(CheckIn.class).save(novoCheckin, new AsyncCallback<CheckIn>() {
            @Override
            public void handleResponse(CheckIn checkIn) {
                Log.i("sucess", "salvou");
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.e("erro", "nao salvou"+ backendlessFault.toString());
            }
        });
    }

    private void tentaChecarBackendlessUltimoCheckin() {
        Log.i("d", InitialConfig.user.getEmail());
        Backendless.Data.mapTableToClass("checkin", CheckIn.class);
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        StringBuilder whereClause = new StringBuilder();
        whereClause.append( "usuario" );
        whereClause.append( ".objectId='" ).append( InitialConfig.user.getObjectId()).append( "'" );
        whereClause.append( " and " );
        whereClause.append( "palestra.id = '"+palestraAtual.getId()+"'" );
        dataQuery.setWhereClause( whereClause.toString() );

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addRelated("Users");
        queryOptions.addRelated( "Palestra" );
        //queryOptions.addRelated("palestrante");
//        queryOptions.setPageSize(20);
        dataQuery.setQueryOptions( queryOptions );
        Log.i("Query", dataQuery.getWhereClause());
        Backendless.Data.of("CheckIn").find(dataQuery, new AsyncCallback<BackendlessCollection<Map>>() {
            @Override
            public void handleResponse(BackendlessCollection<Map> collection) {
                if(collection.getData().isEmpty()) {
                    CheckIn novoCheckin = new CheckIn();
                    novoCheckin.setUsuario(new Usuario(InitialConfig.user.getObjectId()));
                    novoCheckin.setPalestra(palestraAtual);
                    novoCheckin.setHoraEntrada(new Date());
                    salvaCheckinNoBkl(novoCheckin);
                    view.checkinSucess();
                } else {
                    view.usuarioChecked();
                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.e("Erro", backendlessFault.toString());
            }
        });
    }

    @Override
    public void tentaCheckin(String code) {
        if(code.length() > 5 && code.substring(0,5).equals("SEA3:")) {

            if(palestraAtual.getCodigoQrCode().equals(code)) {
                tentaChecarBackendlessUltimoCheckin();
            } else {
                view.qrCodeNaoValido();
            }
        } else {
            view.qrCodeNaoValido();
        }
    }
}
