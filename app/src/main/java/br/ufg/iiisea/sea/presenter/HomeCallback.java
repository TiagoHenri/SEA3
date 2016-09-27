package br.ufg.iiisea.sea.presenter;

/**
 * Created by fellipe on 20/09/16.
 */
public interface HomeCallback {

    interface OnLoadingListener {
        void onError(int type, String msg);
        void onSucess();
    }
}
