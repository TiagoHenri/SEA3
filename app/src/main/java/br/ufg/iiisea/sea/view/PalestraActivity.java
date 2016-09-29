package br.ufg.iiisea.sea.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.presenter.PalestraPresenter;
import br.ufg.iiisea.sea.presenter.PalestraPresenterImpl;

/**
 * Created by Tiago on 29/09/16.
 */
public class PalestraActivity extends AppCompatActivity implements PalestraView {

    private ProgressDialog progress;

    private PalestraPresenter presenter;

    private TextView tvNome = null;
    private TextView tvPalestrantes = null;
    private TextView tvHoraInicio = null;
    private TextView tvHoraFim = null;
    private Button btnCheckIn = null;

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
}
