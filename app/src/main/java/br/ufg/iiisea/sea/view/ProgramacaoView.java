package br.ufg.iiisea.sea.view;

import br.ufg.iiisea.sea.bean.Palestra;

import java.util.List;

/**
 * Created by fellipe on 21/09/16.
 */
public interface ProgramacaoView {

    void addPalestra(Palestra palestra);
    void addPalestra(List<Palestra> palestraList);
    void removePalestra(Palestra palestra);
    void showNenhumaPalestraDialog();
    void concluidoBusca();
//    void showDescricao(Palestra palestra);
//    void hideDescricao(Palestra palestra);

}
