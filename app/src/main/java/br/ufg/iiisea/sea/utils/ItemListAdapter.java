package br.ufg.iiisea.sea.utils;

import android.view.View;
import android.view.ViewGroup;
import br.ufg.iiisea.sea.utils.ListableBean;

/**
 * Created by fellipe on 23/09/16.
 */
public interface ItemListAdapter<T extends ListableBean> {
    View getView(T item, View view, ViewGroup viewGroup);
}
