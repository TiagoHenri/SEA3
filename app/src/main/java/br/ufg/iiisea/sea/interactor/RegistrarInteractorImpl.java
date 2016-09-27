package br.ufg.iiisea.sea.interactor;

import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import br.ufg.iiisea.sea.R;
import br.ufg.iiisea.sea.control.InitialConfig;
import br.ufg.iiisea.sea.presenter.EntrarCallback;
import br.ufg.iiisea.sea.presenter.RegistrarCallback;

/**
 * Created by tiago on 24/09/16.
 */
public class RegistrarInteractorImpl implements RegistrarInteractor {


    @Override
    public void registrar(String nome, String email, String senha, final RegistrarCallback.OnRegistroListener onRegistroFinishedObj) {
        if(email.equalsIgnoreCase("") || senha.equalsIgnoreCase("") || nome.equalsIgnoreCase("")){
            onRegistroFinishedObj.onErrorCampoVazio();
        } else if(senha.length() < 6) {
            onRegistroFinishedObj.onErrorTamanhoSenha();
        } else {
            BackendlessUser user = new BackendlessUser();
            user.setProperty("name", nome);
            user.setProperty("email", email);
            user.setPassword(senha);

            Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                @Override
                public void handleResponse(BackendlessUser backendlessUser) {
                    onRegistroFinishedObj.onSucess();
                }

                @Override
                public void handleFault(BackendlessFault backendlessFault) {
                    //Tomar cuidado com esse 1 pois é só um tipo
                    onRegistroFinishedObj.onError(1, backendlessFault.getMessage());
                }
            });
        }
    }
}
