package br.ufg.iiisea.sea.view;


import br.ufg.iiisea.sea.utils.ViewAbstract;

/**
 * Created by fellipe on 20/09/16.
 */
public interface EntrarView extends ViewAbstract {

    /**
     * Habilita e mostra o content de progresso do android com a mensagem de estar logando.
     * @param
     */
    void showProgressLogin();

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
