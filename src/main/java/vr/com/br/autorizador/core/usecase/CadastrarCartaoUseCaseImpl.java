package vr.com.br.autorizador.core.usecase;

import vr.com.br.autorizador.core.domain.Cartao;
import vr.com.br.autorizador.core.domain.repository.CartaoRepository;
import vr.com.br.autorizador.core.usecase.input.CadastrarCartaoInput;

import java.awt.desktop.PrintFilesEvent;
import java.math.BigDecimal;

/**
 * Classe que implementa o comportamento do caso de uso de cadastrar cartão
 * @author Eduardo Costa
 * */
public class CadastrarCartaoUseCaseImpl implements CadastrarCartaoUseCase{

    private final CartaoRepository cartaoRepository;

    /**
     * Construtor padrão
     * */
    public CadastrarCartaoUseCaseImpl(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Cartao execute(CadastrarCartaoInput input) {

        Cartao cartao = Cartao.builder()
                .numeroCartao(input.getNumeroCartao())
                .senha(input.getSenha())
                .saldo(new BigDecimal("500"))
                .build();

        cartaoRepository.salvarCartao(cartao);
        return cartao;

    }
}
