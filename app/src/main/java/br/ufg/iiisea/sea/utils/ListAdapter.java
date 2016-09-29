package br.ufg.iiisea.sea.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
        notifyDataSetChanged();
    }

    public void removeItem(T item) {
        list.remove(item);
        notifyDataSetChanged();
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
        Log.i("Size", Integer.toString(list.size()));
            return itemListAdapter.getView(list.get(i), view, viewGroup);
    }
}
