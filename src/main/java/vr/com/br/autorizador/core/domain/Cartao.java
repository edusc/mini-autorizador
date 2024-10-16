package vr.com.br.autorizador.core.domain;

import lombok.Builder;
import lombok.Data;
import vr.com.br.autorizador.infrastructure.database.model.CartaoModel;

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

    /**
     * Converte um objeto de modelo para um objeto de domínio
     * @param cartaoModel - objeto a ser convertido
     * @return Cartao
     * */
    public static Cartao toDomain(CartaoModel cartaoModel) {
        return Cartao.builder()
                .numeroCartao(cartaoModel.getNumeroCartao())
                .saldo(cartaoModel.getSaldo())
                .senha(cartaoModel.getSenha())
                .build();

    }
}
