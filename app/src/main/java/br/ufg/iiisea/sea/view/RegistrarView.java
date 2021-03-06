package br.ufg.iiisea.sea.view;


import br.ufg.iiisea.sea.utils.ViewAbstract;

/**
 * Created by tiago on 24/09/16.
 */
public interface RegistrarView extends ViewAbstract {

    /**
     * Habilita e mostra o content de progresso do android com a mensagem de estar logando.
     * @param
     */
    void showProgressRegistro();

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

}
