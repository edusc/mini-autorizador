package vr.com.br.autorizador.entrypoint.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RealizarTransacaoRequest {
    private String numeroCartao;
    private String senha;
    private BigDecimal valor;
}
