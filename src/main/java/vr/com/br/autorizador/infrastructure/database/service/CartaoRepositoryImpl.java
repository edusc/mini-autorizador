package vr.com.br.autorizador.infrastructure.database.service;

import vr.com.br.autorizador.core.domain.Cartao;
import vr.com.br.autorizador.core.domain.repository.CartaoRepository;
import vr.com.br.autorizador.entrypoint.exception.CartaoDuplicadoException;
import vr.com.br.autorizador.entrypoint.exception.CartaoNaoEncontradoException;
import vr.com.br.autorizador.infrastructure.database.model.CartaoModel;
import vr.com.br.autorizador.infrastructure.database.repository.CartaoMongoRepository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Classe que implementa as ações de integração com o banco de dados relativos a Cartões
 * @author Eduardo Costa
 * */
public class CartaoRepositoryImpl implements CartaoRepository {

    private final CartaoMongoRepository cartaoMongoRepository;

    private static final String CARTAO_INEXISTENTE = "CARTAO_INEXISTENTE";

    /**
     * Construtor padrão
     * */
    public CartaoRepositoryImpl(CartaoMongoRepository cartaoMongoRepository) {
        this.cartaoMongoRepository = cartaoMongoRepository;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public Cartao salvarCartao(Cartao cartao) {
        CartaoModel cartaoModel = CartaoModel.toModel(cartao);
        try{
            cartaoMongoRepository.save(cartaoModel);
        } catch (Exception exception) {
            throw new CartaoDuplicadoException("CARTAO_DUPLICADO");
        }

        return cartao;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public BigDecimal obterSaldoCartao(String numeroCartao) {
        return cartaoMongoRepository.findByNumeroCartao(numeroCartao)
                .orElseThrow(() -> new CartaoNaoEncontradoException(CARTAO_INEXISTENTE))
                .getSaldo();
    }

    @Override
    public Optional<Cartao> obterCartaoPeloNumero(String numeroCartao) {
        CartaoModel cartaoModel = cartaoMongoRepository.findByNumeroCartao(numeroCartao)
                .orElseThrow(() -> new CartaoNaoEncontradoException(CARTAO_INEXISTENTE));

        return Optional.of(Cartao.toDomain(cartaoModel));
    }

    @Override
    public void atualizarCartao(Cartao cartao) {
        CartaoModel cartaoModel = cartaoMongoRepository.findByNumeroCartao(cartao.getNumeroCartao())
                .orElseThrow(() -> new CartaoNaoEncontradoException(CARTAO_INEXISTENTE));

        cartaoModel.setSaldo(cartao.getSaldo());
        cartaoMongoRepository.save(cartaoModel);
    }

}
