package br.ufg.iiisea.sea.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.bean.Programacao;
import br.ufg.iiisea.sea.presenter.PalestraPresenter;
import br.ufg.iiisea.sea.presenter.PalestraPresenterImpl;

/**
 * Created by Tiago on 29/09/16.
 */
public class PalestraActivity extends AppCompatActivity implements PalestraView {

    private ProgressDialog progress;
    private static final String PALESTRA_ATUAL = "palestra_atual";
    private Palestra palestraAtual;
    private PalestraPresenter presenter;

    private TextView tvNome = null;
    private TextView tvPalestrantes = null;
    private TextView tvHoraInicio = null;
    private TextView tvHoraFim = null;
    private Button btnCheckIn = null;

    private int palestraId = 0;

    public PalestraActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palestra);
        presenter = new PalestraPresenterImpl(this);

        tvNome = (TextView) findViewById(R.id.tvPalestraNome);
        tvPalestrantes = (TextView) findViewById(R.id.tvPalestraPalestrantes);
        tvHoraInicio = (TextView) findViewById(R.id.tvPalestraInicio);
        tvHoraFim = (TextView) findViewById(R.id.tvPalestraFim);
        btnCheckIn = (Button) findViewById(R.id.btnPalestraCheckin);

        btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkIn();
            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            try{
                String nome = extras.getString(ProgramacaoFragment.NOME_PALESTRA);
                String palestrantes = extras.getString(ProgramacaoFragment.NOME_PALESTRANTES);
                String horaInicio = extras.getString(ProgramacaoFragment.HORA_INICIO);
                String horaFim = extras.getString(ProgramacaoFragment.HORA_FIM);
                Palestra.Tipo tipo = Palestra.Tipo.valueOf(extras.getString(ProgramacaoFragment.TIPO));
                int id = extras.getInt(ProgramacaoFragment.ID);

                palestraAtual = new Palestra();
                palestraAtual.setId(id);
                palestraAtual.setNome(nome);

                if(tipo.equals(Palestra.Tipo.OUTROS)) {
                    Log.i("i", "igual");
                    btnCheckIn.setEnabled(false);
                    btnCheckIn.setVisibility(View.GONE);
                }

                tvNome.setText(nome);
                tvPalestrantes.setText(palestrantes);
                tvHoraInicio.setText(horaInicio);
                tvHoraFim.setText(horaFim);
                this.palestraId = id;
            }catch (NullPointerException e){

            }
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgressCheckIn() {
        progress = ProgressDialog.show(PalestraActivity.this, getResources().getString(R.string.checkIn),
                getResources().getString(R.string.aguarde), true);
    }

    @Override
    public void hideProgress() {
        progress.dismiss();
    }

    @Override
    public void showToastMessage(int msg) {
        showToastByString(getResources().getString(msg));
    }

    @Override
    public void showToastByString(String msg) {
        Toast.makeText(getApplicationContext(),
                msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void chamaCheckinActivity() {

        Intent myIntent = new Intent(this, CheckinActivity.class);
        Bundle b = new Bundle();
        b.putSerializable(CheckinActivity.PALESTRA_ATUAL, palestraAtual);
        myIntent.putExtra("bundle", b);
        startActivity(myIntent);
    }
}
