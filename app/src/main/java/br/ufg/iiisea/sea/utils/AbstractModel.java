package br.ufg.iiisea.sea.utils;

import android.content.Context;

/**
 * Created by fellipe on 03/10/16.
 */
public class AbstractModel {

    private Context context;

    public AbstractModel(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }
}
