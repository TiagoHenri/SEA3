package br.ufg.iiisea.sea.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Noticia;
import br.ufg.iiisea.sea.control.InitialConfig;
import br.ufg.iiisea.sea.utils.ItemListAdapter;
import br.ufg.iiisea.sea.utils.ListAdapter;
import br.ufg.iiisea.sea.utils.NoticiaFragmentAdapter;

/**
 * Created by tiago on 25/09/2016.
 */
public class NoticiaFragment extends Fragment {

    private ListView lstNoticias = null;

    private ListAdapter<Noticia> lstAdapter;

    public NoticiaFragment() {
        // Required empty public constructor
    }

    public static NoticiaFragment newInstance(int index) {
        NoticiaFragment fragment = new NoticiaFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View convertView = inflater.inflate(R.layout.fragment_noticia, container, false);
        lstNoticias = (ListView) convertView.findViewById(R.id.lstNoticias);

        ItemListAdapter<Noticia> itemListAdapter = new ItemListAdapter<Noticia>() {
            @Override
            public View getView(Noticia item, View view, ViewGroup viewGroup) {
                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(convertView.getContext());
                    view = inflater.inflate(R.layout.noticia_item, viewGroup, false);
                }
                TextView titulo = (TextView) view.findViewById(R.id.tvNoticiaItemTitulo);
                TextView conteudo = (TextView) view.findViewById(R.id.tvNoticiaItemConteudo);
                titulo.setBackgroundColor(Color.BLUE);
                titulo.setText(item.getTitulo());
                conteudo.setText(item.getConteudo() + " - " + item.getId() + " - " + item.getTitulo());
                conteudo.setId((int) item.getId());
                return view;
            }
        };

        //ArrayAdapter<Noticia> ad = new NoticiaFragmentAdapter(convertView.getContext(), R.layout.noticia_item, InitialConfig.noticias);
        lstAdapter = new ListAdapter<>(convertView.getContext(), itemListAdapter);
        lstNoticias.setAdapter(lstAdapter);

        return inflater.inflate(R.layout.fragment_noticia, container, false);
    }
}
