package br.ufg.iiisea.sea.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;
import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Noticia;
import br.ufg.iiisea.sea.control.InitialConfig;
import br.ufg.iiisea.sea.presenter.NoticiaPresenter;
import br.ufg.iiisea.sea.presenter.NoticiaPresenterImpl;
import br.ufg.iiisea.sea.utils.ItemListAdapter;
import br.ufg.iiisea.sea.utils.ListAdapter;
import br.ufg.iiisea.sea.utils.NoticiaFragmentAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by tiago on 25/09/2016.
 */
public class NoticiaFragment extends Fragment implements NoticiaView, SwipeRefreshLayout.OnRefreshListener
{

    private static final String EVENTO_ATUAL = "evento_atual";
    private ListView lstNoticias = null;
    private Evento evento;

    private ListAdapter<Noticia> lstAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NoticiaPresenter presenter;


    public NoticiaFragment() {
    }

    public static final NoticiaFragment newInstance(int index) {
        NoticiaFragment fragment = new NoticiaFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    public static final NoticiaFragment newInstance(Evento eventoAtual) {
        NoticiaFragment fragment = new NoticiaFragment();
        Bundle args = new Bundle();
        args.putSerializable(EVENTO_ATUAL, eventoAtual);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Evento eventoAtual = (Evento) getArguments().getSerializable(EVENTO_ATUAL);
        presenter = new NoticiaPresenterImpl(this, getContext(), eventoAtual);

        ItemListAdapter<Noticia> itemListAdapter = new ItemListAdapter<Noticia>() {
            @Override
            public View getView(Noticia item, View view, ViewGroup viewGroup) {
                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    view = inflater.inflate(R.layout.noticia_item, viewGroup, false);
                }
                Log.i("Entrou", "itemListAdapter:evneto");
                TextView titulo = (TextView) view.findViewById(R.id.tvNoticiaItemTitulo);
                TextView conteudo = (TextView) view.findViewById(R.id.tvNoticiaItemConteudo);
                titulo.setText(item.getTitulo());

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM HH:mm");
                titulo.setText(dateFormat.format(item.getData()) + "\n" + titulo.getText().toString());
                conteudo.setText(item.getConteudo());
                return view;
            }
        };


        //ArrayAdapter<Noticia> ad = new NoticiaFragmentAdapter(convertView.getContext(), R.layout.noticia_item, InitialConfig.noticias);
        lstAdapter = new ListAdapter<>(getContext(), itemListAdapter);

        presenter.preparaNoticiasInicial();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Entrou", "onCreateView");
        final View convertView = inflater.inflate(R.layout.fragment_noticia, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) convertView.findViewById(R.id.swipe_refresh_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(android.R.color.holo_green_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark);
        lstNoticias = (ListView) convertView.findViewById(R.id.lstNoticias);

        TextView tvTeste = (TextView) convertView.findViewById(R.id.tvTeste);
        Evento e = (Evento) getArguments().getSerializable(EVENTO_ATUAL);
        String teste = (e != null) ? e.getNome() : "nao veio";
        tvTeste.setText(teste);

        lstNoticias.setAdapter(lstAdapter);


        return convertView;
    }

    @Override
    public void finish() {

    }

    @Override
    public void addNoticia(Noticia newNoticia) {
        lstAdapter.addItem(newNoticia);
    }

    @Override
    public void addNoticia(List<Noticia> lista) {
        for(int i = 0; i < lista.size(); i++)
            lstAdapter.addItem(lista.get(i));
    }

    @Override
    public void removeNoticia(Noticia oldNoticia) {
        Log.i("e", "removeNoticia:viewfragment");
        lstAdapter.removeItem(oldNoticia);
    }

    @Override
    public void showNenhumaNoticiaMessage() {
        Toast.makeText(getContext(),
                "Não há mais notícias para esse evento.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
//        swipeRefreshLayout.setRefreshing(false);
//        showNenhumaNoticiaMessage();
        presenter.atualizarNoticias();
    }

    @Override
    public void concluidoAtualizacao() {
        swipeRefreshLayout.setRefreshing(false);
    }
}
