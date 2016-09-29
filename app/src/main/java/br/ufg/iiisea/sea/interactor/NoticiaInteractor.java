package br.ufg.iiisea.sea.interactor;

import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Noticia;
import br.ufg.iiisea.sea.presenter.HomeCallback;
import br.ufg.iiisea.sea.utils.ViewPagerAdapter;

import java.util.List;

/**
 * Created by fellipe on 23/09/16.
 */
public interface NoticiaInteractor {

    List<Noticia> getNoticiasInicio();
    void atualizarNoticias();
}
