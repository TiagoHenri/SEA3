package br.ufg.iiisea.sea.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Programacao;
import br.ufg.iiisea.sea.presenter.ProgramacaoPresenter;
import br.ufg.iiisea.sea.presenter.ProgramacaoPresenterImpl;
import br.ufg.iiisea.sea.utils.ItemListAdapter;
import br.ufg.iiisea.sea.utils.ListAdapter;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

/**
 * Created by fellipe on 30/09/16.
 */
public class ProgramacaoFragment  extends Fragment implements ProgramacaoView, SwipeRefreshLayout.OnRefreshListener {

    private static final String PROGRAMACAO_ATUAL = "programacao_atual";
    private ListView lstPalestra;
    private ProgramacaoPresenter presenter;
    private ListAdapter<Palestra> lstAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Programacao programacaoAtual;

    public ProgramacaoFragment() {
    }

    public static final ProgramacaoFragment newInstance(Programacao prog) {
        ProgramacaoFragment fragment = new ProgramacaoFragment();
        Bundle args = new Bundle();
        args.putSerializable(PROGRAMACAO_ATUAL, prog);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        programacaoAtual = (Programacao) getArguments().getSerializable(PROGRAMACAO_ATUAL);
        presenter = new ProgramacaoPresenterImpl(this, getContext(), programacaoAtual );

        ItemListAdapter<Palestra> itemListAdapter = new ItemListAdapter<Palestra>() {
            @Override
            public View getView(final Palestra item, View view, ViewGroup viewGroup) {
                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    view = inflater.inflate(R.layout.palestra_item, viewGroup, false);
                }
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
                TextView tvPalestra = (TextView) view.findViewById(R.id.tvPalestraItemTitulo);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
                tvPalestra.setText(item.getNome()+ " " + dateFormat.format(item.getHoraInicio())+ " até " + dateFormat.format(item.getHoraFim()));
                return view;
            }
        };

        lstAdapter = new ListAdapter<>(getContext(), itemListAdapter);
        presenter.preparaPaletrasInicial();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View convertView = inflater.inflate(R.layout.fragment_programacao, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) convertView.findViewById(R.id.srl_programacao_fragment);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(android.R.color.holo_green_dark,
                android.R.color.holo_red_dark,
                android.R.color.holo_blue_dark,
                android.R.color.holo_orange_dark);
        lstPalestra = (ListView) convertView.findViewById(R.id.lstPalestras);
        Log.i("E", "entrou");
        lstPalestra.setAdapter(lstAdapter);
        return convertView;
    }


    @Override
    public void addPalestra(List<Palestra> palestraList) {
        for(Iterator<Palestra> iterator = palestraList.iterator(); iterator.hasNext();) {
            Palestra next = iterator.next();
            addPalestra(next);
        }
    }

    @Override
    public void addPalestra(Palestra palestra) {
        lstAdapter.addItem(palestra);
    }

    @Override
    public void removePalestra(Palestra palestra) {
        lstAdapter.removeItem(palestra);
    }

    @Override
    public void showNenhumaPalestraDialog() {
        Toast.makeText(getContext(),
                "Não há palestras novas para essa programação.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void concluidoBusca() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        presenter.atualizarPalestras();
    }
}
