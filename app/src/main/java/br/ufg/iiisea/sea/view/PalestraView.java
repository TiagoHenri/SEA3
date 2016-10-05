package br.ufg.iiisea.sea.view;

import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.utils.ViewAbstract;

/**
 * Created by Tiago on 29/09/2016.
 */

public interface PalestraView extends ViewAbstract {
    /**
     * Habilita e mostra o content de progresso do android com a mensagem de estar realizando o checkIn.
     * @param
     */
    void showProgressCheckIn();

    /**
     * Desabilita ou esconde o content de progresso do android.
     */
    void hideProgress();

    /**
     * Mostra uma mensagem como um Toast na Tela de acordo com o tipo da mensagem vinda pelo arquivo R
     *
     * @param msg
     */
    void showToastMessage(int msg);

    /**
     * Mostra uma mensagem como um Toast via uma string vinda do presenter.
     * @param msg
     */
    void showToastByString(String msg);

    void chamaCheckinActivity(Palestra palestraAtualizada);
}
