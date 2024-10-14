package vr.com.br.autorizador.core.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Classe de negócio para entidade Cartão
 * @author Eduardo Costa
 * */
@Data
@Builder
public class Cartao {
    private String numeroCartao;
    private String senha;
    private BigDecimal saldo;
}
