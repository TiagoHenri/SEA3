package br.ufg.iiisea.sea.view;

import br.ufg.iiisea.sea.bean.Palestra;

/**
 * Created by fellipe on 21/09/16.
 */
public interface ProgramacaoView {

    void addPalestra(Palestra palestra);
    void removePalestra(Palestra palestra);
    void showDescricao(Palestra palestra);
    void hideDescricao(Palestra palestra);

}
