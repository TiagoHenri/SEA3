package br.ufg.iiisea.sea.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.control.InitialConfig;

public class Entrar extends AppCompatActivity {

    private ProgressDialog progress;

    private EditText etEmail = null;
    private EditText etSenha = null;
    private TextView tvEsqueceuSenha = null;
    private Button btnEntrar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);
        etEmail = (EditText) findViewById(R.id.etLoginEmail);
        etSenha = (EditText) findViewById(R.id.etLoginSenha);
        tvEsqueceuSenha = (TextView) findViewById(R.id.tvLoginEsqueciSenha);
        btnEntrar = (Button) findViewById(R.id.btnLoginConcluir);

        tvEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrar.this, EsqueciSenha.class);
                startActivity(intent);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                if(email.equalsIgnoreCase("") || senha.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.erroCampoVazio), Toast.LENGTH_SHORT).show();
                } else if(senha.length() < 6) {
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.erroTamanhoSenha), Toast.LENGTH_SHORT).show();
                } else {
                    progress = ProgressDialog.show(Entrar.this, getResources().getString(R.string.logando),
                            getResources().getString(R.string.aguarde), true);

                    Backendless.UserService.login(email, senha, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser backendlessUser) {
                            progress.dismiss();
                            InitialConfig.isLogged = true;
                            InitialConfig.user = backendlessUser;
                            Intent intent = new Intent(Entrar.this, Home.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.msgBemVindo) + backendlessUser.getProperty("name").toString(),
                                    Toast.LENGTH_LONG).show();
                            finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault backendlessFault) {
                            progress.dismiss();
                            Toast.makeText(getApplicationContext(),
                                    backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }, true);
                }

            }
        });
    }
}
