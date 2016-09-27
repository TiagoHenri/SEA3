package br.ufg.iiisea.sea.interactor;

import br.ufg.iiisea.sea.presenter.EsqueciSenhaCallback;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

/**
 * Created by fellipe on 21/09/16.
 */
public class EsqueciSenhaInteractorImpl implements EsqueciSenhaInteractor {

    private EsqueciSenhaCallback.OnEsqueciSenhaListener onEsqueciSenhaListener;

    public EsqueciSenhaInteractorImpl(EsqueciSenhaCallback.OnEsqueciSenhaListener onEsqueciSenhaListener) {
        this.onEsqueciSenhaListener = onEsqueciSenhaListener;
    }

    @Override
    public void realizaEsqueciSenha(String email) {
        if(email.equalsIgnoreCase("")){
            onEsqueciSenhaListener.onErrorEmailInvalido();
        } else {
            onEsqueciSenhaListener.onLoad();

            Backendless.UserService.restorePassword(email,
                    new AsyncCallback<Void>() {
                        @Override
                        public void handleResponse(Void aVoid) {
                            onEsqueciSenhaListener.onSucess();
                        }

                        @Override
                        public void handleFault(BackendlessFault backendlessFault) {
                            onEsqueciSenhaListener.onError(backendlessFault.getMessage()    );
                        }
                    });
        }
    }
}
