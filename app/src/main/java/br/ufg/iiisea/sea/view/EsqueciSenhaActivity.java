package br.ufg.iiisea.sea.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.presenter.EsqueciSenhaPresenter;
import br.ufg.iiisea.sea.presenter.EsqueciSenhaPresenterImpl;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

/**
 * Created by fellipe on 21/09/16.
 */
public class EsqueciSenhaActivity extends AppCompatActivity implements EsqueciSenhaView {


    private ProgressDialog progress;

    private EditText etEmail = null;
    private Button btnConcluir = null;

    private EsqueciSenhaPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        etEmail = (EditText) findViewById(R.id.etEsquciSenhaEmail);
        btnConcluir = (Button) findViewById(R.id.btnEsqueciSenhaEnviar);

        presenter = new EsqueciSenhaPresenterImpl(this);

        btnConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                presenter.validaEsqueciSenha(email);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    /**
     * Habilita ou mostra o content de progresso.
     */
    @Override
    public void showProgress() {
        progress = ProgressDialog.show(EsqueciSenhaActivity.this, getResources().getString(R.string.aguarde),
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

        Toast.makeText(getApplicationContext(),
                getResources().getString(msg), Toast.LENGTH_LONG).show();
    }

    /**
     * Mostra uma mensagem como um Toast via uma string vinda do presenter.
     *
     * @param msg
     */
    @Override
    public void showToastByString(String msg) {

        Toast.makeText(getApplicationContext(),
                msg, Toast.LENGTH_LONG).show();
    }
}
