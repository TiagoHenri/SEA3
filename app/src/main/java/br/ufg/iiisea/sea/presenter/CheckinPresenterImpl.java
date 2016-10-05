package br.ufg.iiisea.sea.presenter;

import br.ufg.iiisea.sea.bean.CheckIn;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.view.CheckinView;
import com.backendless.Backendless;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;

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

    private void salvaNoBackendlessCheckin() {

    }

    private void tentaChecarBackendlessUltimoCheckin() {
        Backendless.Data.mapTableToClass("checkin", CheckIn.class);
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        String query = "";
        dataQuery.setWhereClause( query );

        QueryOptions queryOptions = new QueryOptions();
        queryOptions.addRelated( "usuario" );
        //queryOptions.addRelated("palestrante");
//        queryOptions.setPageSize(20);
        dataQuery.setQueryOptions( queryOptions );
    }

    @Override
    public void tentaCheckin(String code) {
        if(code.length() > 5 && code.substring(0,4).equals("SEA3:")) {
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
