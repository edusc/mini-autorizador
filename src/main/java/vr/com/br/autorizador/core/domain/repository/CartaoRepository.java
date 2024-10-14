package vr.com.br.autorizador.core.domain.repository;

import vr.com.br.autorizador.core.domain.Cartao;

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
}
