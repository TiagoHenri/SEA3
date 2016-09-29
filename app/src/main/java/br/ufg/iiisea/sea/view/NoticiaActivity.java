package br.ufg.iiisea.sea.view;

import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Evento;
import br.ufg.iiisea.sea.bean.Noticia;
import br.ufg.iiisea.sea.dao.NoticiaDAO;
import br.ufg.iiisea.sea.presenter.NoticiaPresenter;
import br.ufg.iiisea.sea.presenter.NoticiaPresenterImpl;
import br.ufg.iiisea.sea.utils.ItemListAdapter;
import br.ufg.iiisea.sea.utils.ListAdapter;

import java.util.List;

public class NoticiaActivity extends AppCompatActivity implements NoticiaView {
    private ListView lvNoticia;
    private ListAdapter<Noticia> listAdapter;

    private NoticiaPresenter presenter;


    /*public NoticiaActivity() {
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_noticia);
//
//        //presenter = new NoticiaPresenterImpl(this, getApplicationContext(), );
//
//        ItemListAdapter<Noticia> itemListAdapter = new ItemListAdapter<Noticia>() {
//            @Override
//            public View getView(Noticia item, View convertView, ViewGroup parent) {
//                View view = convertView;
//                if(view == null) {
//                    LayoutInflater inflater = getLayoutInflater();
//                    view = inflater.inflate(R.layout.noticia_item, parent, false);
//                }
//                TextView titulo = (TextView) view.findViewById(R.id.tvNoticiaItemTitulo);
//                TextView conteudo = (TextView) view.findViewById(R.id.tvNoticiaItemConteudo);
//                titulo.setBackgroundColor(Color.BLUE);
//                titulo.setText(item.getTitulo());
//                conteudo.setText(item.getConteudo() + " - " + item.getId() + " - " + item.getTitulo());
//                conteudo.setId((int) item.getId());
//                return view;
//            }
//        };
//        listAdapter = new ListAdapter<>(getApplicationContext(), itemListAdapter);
//
//        lvNoticia = (ListView) findViewById(R.id.lvNoticia);
//        lvNoticia.setAdapter(listAdapter);
//        presenter.preparaNoticiasInicial();
//
//        final EditText titulo, conteudo;
//        titulo = (EditText) findViewById(R.id.titulo);
//        conteudo = (EditText) findViewById(R.id.conteudo);
//        Button button = (Button) findViewById(R.id.button);
//        Button button2 = (Button) findViewById(R.id.button2);
//
//        final NoticiaDAO noticiaDAO = new NoticiaDAO(getApplicationContext());
//        final Evento eventoItem = new Evento(1);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Noticia item = new Noticia();
//                item.setTitulo(titulo.getText().toString());
//                item.setConteudo(conteudo.getText().toString());
//                item.setEvento(eventoItem);
//                try {
//                    noticiaDAO.save(item);
//                    presenter.atualizarNoticias();
//                    Toast.makeText(getApplicationContext(),
//                            "added", Toast.LENGTH_SHORT).show();
//                } catch (SQLiteException ex) {
//                    Log.e("sql", ex.toString());
//                    Toast.makeText(getApplicationContext(),
//                            "impossivel add", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                removeNoticia(new Noticia(0));
//            }
//        });

    }


    @Override
    public void addNoticia(Noticia newNoticia) {
        listAdapter.addItem(newNoticia);
    }

    @Override
    public void addNoticia(List<Noticia> lista) {
        for(int i = 0; i < lista.size(); i++)
            addNoticia(lista.get(i));
    }

    @Override
    public void removeNoticia(Noticia item) {
        listAdapter.removeItem(item );
    }

    @Override
    public void concluidoAtualizacao() {

    }

    @Override
    public void showNenhumaNoticiaMessage() {

        Toast.makeText(getApplicationContext(),
                "Nenhuma notícia para esse evento.", Toast.LENGTH_SHORT).show();
    }
}
