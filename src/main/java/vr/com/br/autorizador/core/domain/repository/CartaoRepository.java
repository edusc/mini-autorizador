package vr.com.br.autorizador.core.domain.repository;

import vr.com.br.autorizador.core.domain.Cartao;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Classe que define as ações para as operações de cartão
 * @author Eduardo Costa
 * */
public interface CartaoRepository {

    /**
     * Salva um registro de cartão no banco de dados.
     * @param cartao - Cartao a ser inserido
     * @return Cartao
     * */
    Cartao salvarCartao(Cartao cartao);

    /**
     * Obtém o saldo do cartão informado
     * @param numeroCartao - número do cartão a ser consultado
     * @return  BigDecimal
     * */
    BigDecimal obterSaldoCartao(String numeroCartao);

    /**
     * Obtém o cartão pelo número informado
     * @param - numeroCartao
     * @return Optional<Cartao>
     * */
    Optional<Cartao> obterCartaoPeloNumero(String numeroCartao);

    void atualizarCartao(Cartao cartao);

}
