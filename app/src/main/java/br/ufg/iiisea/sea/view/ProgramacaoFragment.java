package br.ufg.iiisea.sea.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Programacao;
import br.ufg.iiisea.sea.presenter.ProgramacaoPresenter;
import br.ufg.iiisea.sea.presenter.ProgramacaoPresenterImpl;
import br.ufg.iiisea.sea.utils.ItemListAdapter;
import br.ufg.iiisea.sea.utils.ListAdapter;

/**
 * Created by fellipe on 30/09/16.
 */
public class ProgramacaoFragment  extends Fragment implements ProgramacaoView {

    private static final String PROGRAMACAO_ATUAL = "programacao_atual";
    private ListView lstPalestra;
    private ProgramacaoPresenter presenter;
    private ListAdapter<Palestra> lstAdapter;

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
            public View getView(Palestra item, View view, ViewGroup viewGroup) {
                if(view == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    view = inflater.inflate(R.layout.noticia_item, viewGroup, false);
                }
                TextView tvPalestra = (TextView) view.findViewById(R.id.tvPalestra);
                tvPalestra.setText(item.getNome() + " - " + item.getTipo().toString());
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
        lstPalestra = (ListView) convertView.findViewById(R.id.lstPalestras);
        lstPalestra.setAdapter(lstAdapter);
        return convertView;
    }




    @Override
    public void addPalestra(Palestra palestra) {

    }

    @Override
    public void removePalestra(Palestra palestra) {

    }
}
