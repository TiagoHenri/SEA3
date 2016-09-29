package br.ufg.iiisea.sea.interactor;

import android.content.Context;
import android.util.Log;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.dao.EventoDAO;
import br.ufg.iiisea.sea.presenter.HomeCallback;
import com.backendless.Backendless;

import java.util.Date;

/**
 * Created by fellipe on 27/09/16.
 */
public class HomeInteractorImpl implements HomeInteractor {

    private EventoDAO eventoDAO;
//    private ProgramacaoDAO programacaoDAO;
    private HomeCallback homeCallback;

    public HomeInteractorImpl(Context context, HomeCallback homeCallback) {
        eventoDAO = new EventoDAO(context);
        this.homeCallback = homeCallback;
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
            // aqui pesquisa as programações salvas
            homeCallback.dadosIniciaisEncontrado(aux, null);
        }
    }
}
