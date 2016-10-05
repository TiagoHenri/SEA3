package br.ufg.iiisea.sea.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    public static final String NOME_PALESTRA = "nome_palestra";
    public static final String NOME_PALESTRANTES = "nome_palestrantes";
    public static final String HORA_INICIO = "hora_inicio";
    public static final String HORA_FIM = "hora_fim";
    public static final String TIPO = "tipo";
    public static final String ID = "id";

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
            public View getView(final Palestra item, View view, final ViewGroup viewGroup) {
                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    view = inflater.inflate(R.layout.palestra_item, viewGroup, false);
                }
                TextView tvPalestra = (TextView) view.findViewById(R.id.tvPalestraItemTitulo);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                tvPalestra.setText(dateFormat.format(item.getHoraInicio()) + " - " + dateFormat.format(item.getHoraFim()) +
                        "\n" + item.getNome());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(viewGroup.getContext(), PalestraActivity.class);

                        Palestra result = item;
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

                        intent.putExtra(NOME_PALESTRA, result.getNome());
                        intent.putExtra(NOME_PALESTRANTES, result.getPalestrante());
                        intent.putExtra(HORA_INICIO, dateFormat.format(result.getHoraInicio()));
                        intent.putExtra(HORA_FIM, dateFormat.format(result.getHoraFim()));
                        intent.putExtra(TIPO, result.getTipo().toString());
                        intent.putExtra(ID, result.getId());
                        Log.e("Inicio", item.getNome() + " - " + item.getId());

                        startActivity(intent);
                    }
                });
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
        lstPalestra.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(convertView.getContext(), PalestraActivity.class);
//
//                Palestra result = (Palestra) lstAdapter.getItem(position);
//                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
//
//                intent.putExtra(NOME_PALESTRA, result.getNome());
//                intent.putExtra(NOME_PALESTRANTES, result.getPalestrante());
//                intent.putExtra(HORA_INICIO, dateFormat.format(result.getHoraInicio()));
//                intent.putExtra(HORA_FIM, dateFormat.format(result.getHoraFim()));
//                intent.putExtra(TIPO, result.getTipo().toString());
//                intent.putExtra(ID, result.getId());
//
//                startActivity(intent);
            }
        });
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
