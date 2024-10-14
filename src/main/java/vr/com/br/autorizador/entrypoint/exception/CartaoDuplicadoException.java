package vr.com.br.autorizador.entrypoint.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CartaoDuplicadoException extends RuntimeException {
    public CartaoDuplicadoException(String mensagem) {
        super(mensagem);
    }
}
