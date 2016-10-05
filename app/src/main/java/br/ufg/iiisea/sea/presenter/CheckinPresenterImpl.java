package br.ufg.iiisea.sea.presenter;

import android.util.Log;
import br.ufg.iiisea.sea.bean.Checkin;
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


    private void salvaCheckinNoBkl(Checkin novoCheckin) {
        Backendless.Persistence.of(Checkin.class).save(novoCheckin, new AsyncCallback<Checkin>() {
            @Override
            public void handleResponse(Checkin checkIn) {
                view.checkinSucess();
                Log.i("sucess", "salvou");
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                view.onError("Não foi possível completar a aquisição.");
                Log.e("erro2", "nao salvou"+ backendlessFault.toString());
            }
        });
    }

    private void tentaChecarBackendlessUltimoCheckin() {
        Log.i("d", InitialConfig.user.getEmail());
        Backendless.Data.mapTableToClass("Checkin", Checkin.class);
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        String query = "usuarioId = '" + InitialConfig.user.getEmail() + "' AND palestraId = " + palestraAtual.getId();
        dataQuery.setWhereClause( query );

        QueryOptions queryOptions = new QueryOptions();
        //queryOptions.addRelated("palestrante");
//        queryOptions.setPageSize(20);
        dataQuery.setQueryOptions( queryOptions );
        Log.i("Query", dataQuery.getWhereClause());
        Backendless.Data.of("CheckIn").find(dataQuery, new AsyncCallback<BackendlessCollection<Map>>() {
            @Override
            public void handleResponse(BackendlessCollection<Map> collection) {
                if(collection.getData().isEmpty()) {
                    Checkin novoCheckin = new Checkin();
                    //novoCheckin.setUsuario(new Usuario(InitialConfig.user.getObjectId()));
                    novoCheckin.setUsuarioId(InitialConfig.user.getEmail());
                    novoCheckin.setPalestraId((int) palestraAtual.getId());
                    novoCheckin.setHoraEntrada(new Date());
                    salvaCheckinNoBkl(novoCheckin);
                } else {
                    view.usuarioChecked();
                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.e("Erro1", backendlessFault.toString());
            }
        });
    }

    @Override
    public void tentaCheckin(String code) {
        if(code.length() > 5 && code.substring(0,5).equals("SEA3:")) {

            if(palestraAtual.getCodigoQrCode().equals(code)) {
                tentaChecarBackendlessUltimoCheckin();
            } else {
                Log.i("codigo 1", code + "-"+palestraAtual.getCodigoQrCode()+"-"+palestraAtual.getNome());
                view.qrCodeNaoValido();
            }
        } else {
            Log.i("codigo 2", code);
            view.qrCodeNaoValido();
        }
    }
}
