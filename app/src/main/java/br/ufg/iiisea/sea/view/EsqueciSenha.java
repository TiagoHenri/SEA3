package br.ufg.iiisea.sea.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.control.InitialConfig;

public class EsqueciSenha extends AppCompatActivity {

    private ProgressDialog progress;

    private EditText etEmail = null;
    private Button btnConcluir = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        etEmail = (EditText) findViewById(R.id.etEsquciSenhaEmail);
        btnConcluir = (Button) findViewById(R.id.btnEsqueciSenhaEnviar);

        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                if(email.equalsIgnoreCase("")){
                    Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.erroCampoVazio), Toast.LENGTH_LONG).show();
                } else {
                    progress = ProgressDialog.show(EsqueciSenha.this, getResources().getString(R.string.aguarde),
                            getResources().getString(R.string.aguarde), true);

                    Backendless.UserService.restorePassword(etEmail.getText().toString(),
                            new AsyncCallback<Void>() {
                                @Override
                                public void handleResponse(Void aVoid) {
                                    progress.dismiss();
                                    Toast.makeText(getApplicationContext(),
                                            getResources().getString(R.string.msgLinkEnviado),
                                            Toast.LENGTH_LONG).show();
                                    finish();
                                }

                                @Override
                                public void handleFault(BackendlessFault backendlessFault) {
                                    Toast.makeText(getApplicationContext(),
                                            backendlessFault.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });
    }
}
