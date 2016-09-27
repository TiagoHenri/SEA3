package br.ufg.iiisea.sea.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by fellipe on 21/09/16.
 */
public abstract class PresenterAbstract {

    public void changeIntent(AppCompatActivity before, Class classe) {
        Intent intent = new Intent(before, classe);
        before.startActivity(intent);
    }
}
