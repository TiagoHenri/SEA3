package br.ufg.iiisea.sea.interactor;

import br.ufg.iiisea.sea.bean.Palestra;

import java.util.List;

/**
 * Created by fellipe on 30/09/16.
 */
public interface ProgramacaoInteractor {
    List<Palestra> buscaPalestraInicio();
    void atualizaPalestras();
}
