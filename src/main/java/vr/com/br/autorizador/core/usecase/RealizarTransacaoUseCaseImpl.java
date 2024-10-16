package vr.com.br.autorizador.core.usecase;

import vr.com.br.autorizador.core.domain.Cartao;
import vr.com.br.autorizador.core.domain.repository.CartaoRepository;
import vr.com.br.autorizador.core.usecase.input.RealizarTransacaoInput;
import vr.com.br.autorizador.entrypoint.exception.CartaoNaoEncontradoException;
import vr.com.br.autorizador.entrypoint.exception.SaldoInsuficienteException;
import vr.com.br.autorizador.entrypoint.exception.SenhaInvalidaException;
import java.util.Optional;

/**
 * Classe que implementa o comportamento do caso de uso de realizar transacoes
 * @author Eduardo Costa
 * */
public class RealizarTransacaoUseCaseImpl implements  RealizarTransacaoUseCase{

    private final CartaoRepository cartaoRepository;

    /**
     * Construtor padrão
     * */
    public RealizarTransacaoUseCaseImpl(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public void execute(RealizarTransacaoInput input) {
        Cartao cartao = cartaoRepository.obterCartaoPeloNumero(input.getNumeroCartao())
                .orElseThrow(() -> new CartaoNaoEncontradoException("CARTAO_INEXISTENTE"));

        Optional.of(cartao)
                .filter(c -> c.getSenha().equals(input.getSenhaCartao()))
                .orElseThrow(() -> new SenhaInvalidaException("SENHA_INVALIDA"));

        Optional.of(cartao)
                .filter(c -> input.getValor().compareTo(c.getSaldo()) <= 0)
                .orElseThrow(() -> new SaldoInsuficienteException("SALDO_INSUFICIENTE"));

        cartao.setSaldo(cartao.getSaldo().subtract(input.getValor()));
        cartaoRepository.atualizarCartao(cartao);

    }
}
