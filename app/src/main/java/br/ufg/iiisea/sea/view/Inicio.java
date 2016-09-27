package br.ufg.iiisea.sea.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.persistence.local.UserTokenStorageFactory;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.control.InitialConfig;

public class Inicio extends AppCompatActivity {

    private Button btnEntrar = null;
    private Button btnRegistrar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        if(InitialConfig.isLogged){
            Intent intent = new Intent(Inicio.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        btnEntrar = (Button) findViewById(R.id.btnInicioEntrar);
        btnRegistrar = (Button) findViewById(R.id.btnInicioRegistrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, EntrarActivity.class);
                startActivity(intent);
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inicio.this, RegistrarActivity.class);
                startActivity(intent);
            }
        });
    }
}
