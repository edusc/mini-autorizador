package vr.com.br.autorizador.core.usecase;

import vr.com.br.autorizador.core.usecase.input.RealizarTransacaoInput;

/**
 * Interface que define o comportamento do caso de uso de realizar transacoes
 * @author Eduardo Costa
 * */
public interface RealizarTransacaoUseCase {

    /**
     * Realiza uma transacao de compra no cartão
     * @param input - dados da transacao
     * */
    void execute(RealizarTransacaoInput input);
}
