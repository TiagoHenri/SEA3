package br.ufg.iiisea.sea.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

        //habilita a câmera em versões android 6.x.x
        int currentApiVersion = Build.VERSION.SDK_INT;
        if(currentApiVersion >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(Inicio.this, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(Inicio.this,
                        Manifest.permission.CAMERA)) {
                    ActivityCompat.requestPermissions(Inicio.this, new String[]
                            {android.Manifest.permission.CAMERA}, 1);
                } else {
                    ActivityCompat.requestPermissions(Inicio.this, new String[]{Manifest.permission.CAMERA},
                            1);
                }
            }
        } else {

        }

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
