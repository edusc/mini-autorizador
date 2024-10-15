package vr.com.br.autorizador.core.usecase;

import vr.com.br.autorizador.core.domain.repository.CartaoRepository;

import java.math.BigDecimal;

/**
 * Classe que implementa o comportamento do caso de uso de obter saldo do cartão
 * @author Eduardo Costa
 * */
public class ObterSaldoCartaoUseCaseImpl implements ObterSaldoCartaoUseCase{

    private final CartaoRepository cartaoRepository;

    /**
     * Construtor padrão
     * */
    public ObterSaldoCartaoUseCaseImpl(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public BigDecimal execute(String numeroCartao) {
        return cartaoRepository.obterSaldoCartao(numeroCartao);
    }
}
