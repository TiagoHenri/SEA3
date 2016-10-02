package br.ufg.iiisea.sea.presenter;

import br.ufg.iiisea.sea.bean.Palestra;

import java.util.List;

/**
 * Created by fellipe on 30/09/16.
 */
public interface ProgramacaoCallback {
    interface PaletrasListener {
        void onNewPalestras(List<Palestra> palestrasNovas);
        void onUpdatedPalestras(List<Palestra> palestrasModificadas);
        void onDeletedPalestras(List<Palestra> palestrasDeletadas);
        void onNoPalestras();
        void onError(String msg);
    }
}
