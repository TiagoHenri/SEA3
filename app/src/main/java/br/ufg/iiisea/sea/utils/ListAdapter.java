package br.ufg.iiisea.sea.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fellipe on 23/09/16.
 */
public class ListAdapter<T extends ListableBean> extends BaseAdapter {

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

    public void removeItem(long itemID) {
        Log.i("inicio", Integer.toString((int) itemID));
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == itemID) {
                this.list.remove(i);
                Log.i("Remove", "Removeu o " +Integer.toString((int) itemID) + " o i="+i);
                notifyDataSetChanged();
            }
            Log.i(":", Integer.toString((int) list.get(i).getId()));
        }
        for(int i = 0; i < list.size(); i++)
            Log.d(":", Integer.toString((int) list.get(i).getId()));
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
        try {
            return itemListAdapter.getView(list.get(i), view, viewGroup);
        } catch (NullPointerException ex) {
            Log.e("Erro getView do adapter", "erro nullpointerexception");
            return view;
        }
    }
}
