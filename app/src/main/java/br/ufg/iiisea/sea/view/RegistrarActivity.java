package br.ufg.iiisea.sea.view;

import android.app.ProgressDialog;
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
import br.ufg.iiisea.sea.presenter.RegistrarPresenter;
import br.ufg.iiisea.sea.presenter.RegistrarPresenterImpl;

/**
 * Created by tiago on 24/09/16.
 */
public class RegistrarActivity extends AppCompatActivity implements RegistrarView {

    private ProgressDialog progress;

    private EditText etNome = null;
    private EditText etEmail = null;
    private EditText etSenha = null;
    private Button btnRegistrar = null;

    private RegistrarPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        presenter = new RegistrarPresenterImpl(this);

        etNome = (EditText) findViewById(R.id.etCadastroNome);
        etEmail = (EditText) findViewById(R.id.etCadastroEmail);
        etSenha = (EditText) findViewById(R.id.etCadastroSenha);
        btnRegistrar = (Button) findViewById(R.id.btnCadastroConcluir);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                if(email.equalsIgnoreCase("") || senha.equalsIgnoreCase("") || nome.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.erroCampoVazio), Toast.LENGTH_LONG).show();
                } else if(senha.length() < 6) {
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.erroTamanhoSenha), Toast.LENGTH_LONG).show();
                } else {
                    presenter.validaRegistro(nome, email, senha);
                }
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
    public void showProgressRegistro() {
        progress = ProgressDialog.show(RegistrarActivity.this, getResources().getString(R.string.registrando),
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
