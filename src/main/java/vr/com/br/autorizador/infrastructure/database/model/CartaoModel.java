package vr.com.br.autorizador.infrastructure.database.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import vr.com.br.autorizador.core.domain.Cartao;

import java.math.BigDecimal;

/**
 * Representa a collection do banco de dados de Cartoes
 * @author Eduardo Costa
 * */
@Document(collection = "cartoes")
@Data
@Builder
public class CartaoModel {

    @Id
    private String id;
    @Indexed(unique = true)
    private String numeroCartao;
    private String senha;
    private BigDecimal saldo;

    /**
     * Converte um objeto de negócio em um objeto de banco de dados
     * @param cartao - objeto de negócio a ser convertido
     * @return CartaoModel
     * */
    public static CartaoModel toModel(Cartao cartao) {
        return CartaoModel.builder()
                .numeroCartao(cartao.getNumeroCartao())
                .senha(cartao.getSenha())
                .saldo(cartao.getSaldo())
                .build();
    }

}
