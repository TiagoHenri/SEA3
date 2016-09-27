package br.ufg.iiisea.sea.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.presenter.EntrarPresenter;
import br.ufg.iiisea.sea.presenter.EntrarPresenterImpl;

/**
 * Created by fellipe on 20/09/16.
 */
public class EntrarActivity extends AppCompatActivity implements EntrarView {

    private ProgressDialog progress;

    private EditText etEmail = null;
    private EditText etSenha = null;
    private TextView tvEsqueceuSenha = null;
    private Button btnEntrar = null;

    private EntrarPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);
        presenter = new EntrarPresenterImpl(this);

        etEmail = (EditText) findViewById(R.id.etLoginEmail);
        etSenha = (EditText) findViewById(R.id.etLoginSenha);
        tvEsqueceuSenha = (TextView) findViewById(R.id.tvLoginEsqueciSenha);
        btnEntrar = (Button) findViewById(R.id.btnLoginConcluir);


        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                presenter.validaLogin(email, senha);
            }
        });

        tvEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.esqueceuSenha();
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Habilita e mostra o content de progresso do android com a mensagem de estar logando.
     */
    @Override
    public void showProgressLogin() {
        progress = ProgressDialog.show(EntrarActivity.this, getResources().getString(R.string.logando),
                getResources().getString(R.string.aguarde), true);
    }

    /**
     * Desabilita ou esconde o content de progresso do android.
     */
    @Override
    public void hideProgress() {
        progress.dismiss();
    }

    /**
     * Mostra uma mensagem como um Toast na Tela de acordo com o tipo da mensagem vinda pelo arquivo R
     *
     * @param msg
     */
    @Override
    public void showToastMessage(int msg) {
        showToastByString(getResources().getString(msg));
    }

    /**
     * Mostra uma mensagem como um Toast via uma string vinda do presenter.
     *
     * @param msg
     */
    @Override
    public void showToastByString(String msg) {
        Toast.makeText(getApplicationContext(),
                msg, Toast.LENGTH_SHORT).show();
    }

}
