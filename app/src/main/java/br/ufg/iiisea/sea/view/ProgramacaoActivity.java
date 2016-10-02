package br.ufg.iiisea.sea.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Palestra;

import java.util.List;

public class ProgramacaoActivity extends AppCompatActivity implements ProgramacaoView {

    private ListView lvPalestras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programacao);

        lvPalestras = (ListView) findViewById(R.id.lvPalestras);
    }

    @Override
    public void addPalestra(Palestra palestra) {

    }

    @Override
    public void addPalestra(List<Palestra> palestraList) {

    }

    @Override
    public void removePalestra(Palestra palestra) {

    }

    @Override
    public void showNenhumaPalestraDialog() {

    }

    @Override
    public void concluidoBusca() {

    }

//    @Override
//    public void showDescricao(Palestra palestra) {
//
//    }
//
//    @Override
//    public void hideDescricao(Palestra palestra) {
//
//    }
}
