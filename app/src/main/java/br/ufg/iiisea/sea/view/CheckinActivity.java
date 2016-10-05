package br.ufg.iiisea.sea.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;
import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.bean.Palestra;
import br.ufg.iiisea.sea.presenter.CheckinPresenter;
import br.ufg.iiisea.sea.presenter.CheckinPresenterImpl;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class CheckinActivity extends AppCompatActivity implements CheckinView {

    public static final String PALESTRA_ATUAL = "palestra_atual";
    private SurfaceView cameraView;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private CheckinPresenter presenter;
    private Palestra palestraAtual;

    private String codeFixo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        Bundle bundle = getIntent().getBundleExtra("bundle");

        palestraAtual = (Palestra) bundle.getSerializable(PALESTRA_ATUAL);
        Log.i("PalestraAtual", palestraAtual.getNome() + " - " + palestraAtual.getCodigoQrCode());
        if(palestraAtual == null)
            Log.i("eh", "deu ruim");

        presenter = new CheckinPresenterImpl(palestraAtual,this);

        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource = new CameraSource.Builder(this, barcodeDetector).setRequestedPreviewSize(640,480).build();

        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() != 0) {


                    cameraView.post(new Runnable() {
                        @Override
                        public void run() {
                            String codeDetectado = barcodes.valueAt(0).displayValue;
                            if(!codeDetectado.equals(codeFixo)) {
                                codeFixo = codeDetectado;
                                presenter.tentaCheckin(codeDetectado);
                            }
                        }
                    });
//                    barcodeInfo.post(new Runnable() {    // Use the post method of the TextView
//                        public void run() {
//                            barcodeInfo.setText(    // Update the TextView
//                                    barcodes.valueAt(0).displayValue
//                            );
//                        }
//                    });
                }
            }
        });
    }

    @Override
    public void checkinSucess() {
        //Aqui vc decide sobre o que fazer na tela com o checkin sucesso

        cameraSource.stop();
        Toast.makeText(getApplicationContext(),
                "Sucesso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void qrCodeNaoValido() {

        Toast.makeText(getApplicationContext(),
                "QR-Code não é válido!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void usuarioChecked() {

        cameraSource.stop();
        Toast.makeText(getApplicationContext(),
                "Usuário já fez check-in nessa palestra.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(String msg) {

    }
}
