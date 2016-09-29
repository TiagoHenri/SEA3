package br.ufg.iiisea.sea.presenter;

import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Programacao;

import java.util.List;

/**
 * Created by fellipe on 20/09/16.
 */
public interface HomeCallback {

    void dadosIniciaisEncontrado(Evento evento, List<Programacao> programacaoList);
    void houveErro(String msg);

    interface OnLoadingListener {
        void onError(int type, String msg);
        void onSucess();
    }


}
