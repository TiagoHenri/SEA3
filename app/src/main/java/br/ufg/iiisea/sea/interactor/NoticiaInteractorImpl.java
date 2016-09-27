package br.ufg.iiisea.sea.interactor;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

import java.util.Date;
import java.util.List;

/**
 * Created by fellipe on 23/09/16.
 */
public class NoticiaInteractorImpl implements NoticiaInteractor {

    private NoticiaDAO noticiaDAO;

    public NoticiaInteractorImpl(Context context) {
        this.noticiaDAO = new NoticiaDAO(context);
//        Noticia noticia = new Noticia();
//        noticia.setConteudo("fadfasdfa dfasd fa fasd fa");
//        noticia.setTitulo("noticia1");
//        noticia.setData(new Date());
//        noticia.setEvento(new Evento(1));
//        noticia.setId(1);
//        noticiaDAO.save(noticia);
    }

    @Override
    public List<Noticia> getNoticiasInicio() {

        return noticiaDAO.getAllNoticiaByEvento(new Evento(1));
    }

    @Override
    public void atualizarNoticias(final HomeCallback.OnLoadingListener onLoadingFinishedObj){
        Backendless.Persistence.of(Noticia.class).find(new AsyncCallback<BackendlessCollection<Noticia>>() {
            @Override
            public void handleResponse(BackendlessCollection<Noticia> result) {
                /*for(int i = 0; i < InitialConfig.noticias.size(); i++){
                    InitialConfig.noticias.add(result.getCurrentPage().get(i));
                    Log.e("Noticias: ", InitialConfig.noticias.get(i).getTitulo());
                }*/

                onLoadingFinishedObj.onSucess();

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                //Tomar cuidado com esse 1 pois é só um tipo
                onLoadingFinishedObj.onError(1, backendlessFault.getMessage());
            }
        });
    }
}
