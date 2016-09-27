package br.ufg.iiisea.sea.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Noticia;

/**
 * Created by tiago on 25/09/2016.
 */

public class NoticiaFragmentAdapter extends ArrayAdapter<Noticia> {
    private final LayoutInflater inflater;
    private final int recursoId;


    public NoticiaFragmentAdapter(Context context, int resources, List<Noticia> objects){
        super(context, resources, objects);
        this.inflater = LayoutInflater.from(context);
        this.recursoId = resources;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Noticia noticia = getItem(position);
        convertView = inflater.inflate(recursoId, parent, false);
        TextView tvTitulo = (TextView) convertView.findViewById(R.id.tvNoticiaItemTitulo);
        TextView tvConteudo = (TextView) convertView.findViewById(R.id.tvNoticiaItemConteudo);

        tvTitulo.setText(noticia.getTitulo());
        tvConteudo.setText(noticia.getConteudo());

        return convertView;
    }
}
