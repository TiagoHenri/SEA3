package br.ufg.iiisea.sea.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import br.ufg.iiisea.sea.R;

public class Registrar extends AppCompatActivity {

    private ProgressDialog progress;

    private EditText etNome = null;
    private EditText etEmail = null;
    private EditText etSenha = null;
    private Button btnRegistrar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

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
                    progress = ProgressDialog.show(Registrar.this, getResources().getString(R.string.registrando),
                            getResources().getString(R.string.aguarde), true);

                    BackendlessUser user = new BackendlessUser();
                    user.setProperty("name", nome);
                    user.setProperty("email", email);
                    user.setPassword(senha);

                    Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser backendlessUser) {
                            progress.dismiss();
                            Toast.makeText(getApplicationContext(),
                                    getResources().getString(R.string.sucessoRegistro), Toast.LENGTH_LONG).show();
                            finish();
                        }

                        @Override
                        public void handleFault(BackendlessFault backendlessFault) {
                            progress.dismiss();
                            Toast.makeText(getApplicationContext(),
                                    backendlessFault.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
