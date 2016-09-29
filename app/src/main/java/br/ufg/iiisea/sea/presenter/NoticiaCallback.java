package br.ufg.iiisea.sea.presenter;

import br.ufg.iiisea.sea.bean.Noticia;

import java.util.List;

/**
 * Created by fellipe on 27/09/16.
 */
public interface NoticiaCallback {

    void noticiasNovas(List<Noticia> novasNoticias);
    void noticiasAtualizadas(List<Noticia> noticiasAtualizadas);
    void naoExisteNoticiasNovas();
    void onError(String msg);
}
