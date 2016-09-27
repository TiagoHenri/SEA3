package br.ufg.iiisea.sea.presenter;

import br.ufg.iiisea.sea.bean.Noticia;

import java.util.List;

/**
 * Created by fellipe on 23/09/16.
 */
public interface NoticiaPresenter {
    List<Noticia> preparaNoticiasInicial();
    List<Noticia> atualizarNoticias();
}
