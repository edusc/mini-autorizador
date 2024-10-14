package vr.com.br.autorizador.entrypoint.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vr.com.br.autorizador.core.domain.Cartao;
import vr.com.br.autorizador.core.usecase.CadastrarCartaoUseCase;
import vr.com.br.autorizador.core.usecase.input.CadastrarCartaoInput;
import vr.com.br.autorizador.entrypoint.request.CadastrarCartaoRequest;
import vr.com.br.autorizador.entrypoint.response.CadastrarCartaoResponse;

/**
 * Controller responsável pelas operacoes envolvendo cartões
 * @author Eduardo Costa
 * */
@RestController
@RequestMapping("/cartoes")
public class CartaoRest {

    @Autowired
    private CadastrarCartaoUseCase cadastrarCartaoUseCase;

    @PostMapping
    public ResponseEntity<CadastrarCartaoResponse> cadastrarCartao(@RequestBody @Valid CadastrarCartaoRequest request) {
        Cartao cartao =  cadastrarCartaoUseCase.execute(CadastrarCartaoInput.builder()
                .numeroCartao(request.getNumeroCartao())
                .senha(request.getSenha())
                .build());

        return new ResponseEntity<>(CadastrarCartaoResponse.toResponse(cartao), HttpStatus.CREATED);
    }
}
