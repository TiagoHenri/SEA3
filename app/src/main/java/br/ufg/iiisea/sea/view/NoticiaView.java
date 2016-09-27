package br.ufg.iiisea.sea.view;

import br.ufg.iiisea.sea.bean.Noticia;
import br.ufg.iiisea.sea.utils.ViewAbstract;

import java.util.List;

/**
 * Created by fellipe on 23/09/16.
 */
public interface NoticiaView extends ViewAbstract {
    void addNoticia(Noticia newNoticia);
    void addNoticia(List<Noticia> lista);
    void removeNoticia(Noticia oldNoticia);


    void showNenhumaNoticiaMessage();
}
