package br.ufg.iiisea.sea.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.*;

/**
 * Created by fellipe on 23/09/16.
 */
public class ListAdapter<T extends MutableBean> extends BaseAdapter {

    private Context context;
    private List<T> list;
    private ItemListAdapter<T> itemListAdapter;

    public ListAdapter(Context context, ItemListAdapter<T> itemListAdapter) {
        this.context = context;
        this.itemListAdapter = itemListAdapter;
        this.list = new ArrayList<>();
    }

    public void addItem(T item) {
        this.list.add(item);
        sort();
        notifyDataSetChanged();
    }

    public void removeItem(T item) {

        for(int i = 0; i < list.size(); i++) {
            T aux = list.get(i);
            if(aux.getId() == item.getId())
                list.remove(aux);
        }
        sort();
        notifyDataSetChanged();
    }

    public void sort() {
        Collections.sort(list);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return itemListAdapter.getView(list.get(i), view, viewGroup);
    }
}
