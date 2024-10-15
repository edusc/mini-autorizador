package vr.com.br.autorizador.core.usecase;

import java.math.BigDecimal;

/**
 * Interface que define o comportamento do caso de uso de obter saldo do cartão
 * @author Eduardo Costa
 * */
public interface ObterSaldoCartaoUseCase {

    /**
     * Retorna o saldo do cartão informado
     * @param numeroCartao - cartão a ser consultado
     * @return  BigDecimal
     * */
    BigDecimal execute(String numeroCartao);
}
