package vr.com.br.autorizador.core.usecase.input;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RealizarTransacaoInput {
    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;
}
