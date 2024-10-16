package vr.com.br.autorizador.entrypoint.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vr.com.br.autorizador.core.usecase.RealizarTransacaoUseCase;
import vr.com.br.autorizador.core.usecase.input.RealizarTransacaoInput;
import vr.com.br.autorizador.entrypoint.request.RealizarTransacaoRequest;

/**
 * Controller responsável pelas operacoes envolvendo transacoes
 * @author Eduardo Costa
 * */
@RestController
@RequestMapping("/transacoes")
public class TransacaoRest {

    @Autowired
    private RealizarTransacaoUseCase realizarTransacaoUseCase;

    @PostMapping
    public ResponseEntity<String> realizarTransacao(@RequestBody @Valid RealizarTransacaoRequest request) {
        realizarTransacaoUseCase.execute(RealizarTransacaoInput.builder()
                    .numeroCartao(request.getNumeroCartao())
                    .senhaCartao(request.getSenha())
                    .valor(request.getValor())
                    .build());
        return ResponseEntity.ok("OK");
    }
}
