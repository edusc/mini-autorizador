package vr.com.br.autorizador.entrypoint.rest;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vr.com.br.autorizador.core.domain.Cartao;
import vr.com.br.autorizador.core.usecase.CadastrarCartaoUseCase;
import vr.com.br.autorizador.core.usecase.ObterSaldoCartaoUseCase;
import vr.com.br.autorizador.core.usecase.input.CadastrarCartaoInput;
import vr.com.br.autorizador.entrypoint.request.CadastrarCartaoRequest;
import vr.com.br.autorizador.entrypoint.response.CadastrarCartaoResponse;

import java.math.BigDecimal;

/**
 * Controller responsável pelas operacoes envolvendo cartões
 * @author Eduardo Costa
 * */
@RestController
@RequestMapping("/cartoes")
public class CartaoRest {

    @Autowired
    private CadastrarCartaoUseCase cadastrarCartaoUseCase;

    @Autowired
    private ObterSaldoCartaoUseCase obterSaldoCartaoUseCase;

    @PostMapping
    public ResponseEntity<CadastrarCartaoResponse> cadastrarCartao(@RequestBody @Valid CadastrarCartaoRequest request) {
        Cartao cartao =  cadastrarCartaoUseCase.execute(CadastrarCartaoInput.builder()
                .numeroCartao(request.getNumeroCartao())
                .senha(request.getSenha())
                .build());

        return new ResponseEntity<>(CadastrarCartaoResponse.toResponse(cartao), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<BigDecimal> obterSaldoCartao(@RequestParam("numeroCartao") String numeroCartao) {
        BigDecimal saldo = obterSaldoCartaoUseCase.execute(numeroCartao);
        return new ResponseEntity<>(saldo, HttpStatus.OK);

    }
}
