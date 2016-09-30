package br.ufg.iiisea.sea.presenter;

import br.ufg.iiisea.sea.bean.Palestra;

/**
 * Created by fellipe on 21/09/16.
 */
public interface ProgramacaoPresenter {
    void abrirDescricao(Palestra palestra);
    void preparaPaletrasInicial();
    void atualizarPalestras();
}
