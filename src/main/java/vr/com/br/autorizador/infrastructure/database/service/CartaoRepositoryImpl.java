package vr.com.br.autorizador.infrastructure.database.service;

import vr.com.br.autorizador.core.domain.Cartao;
import vr.com.br.autorizador.core.domain.repository.CartaoRepository;
import vr.com.br.autorizador.entrypoint.exception.CartaoDuplicadoException;
import vr.com.br.autorizador.infrastructure.database.model.CartaoModel;
import vr.com.br.autorizador.infrastructure.database.repository.CartaoMongoRepository;

/**
 * Classe que implementa as ações de integração com o banco de dados relativos a Cartões
 * @author Eduardo Costa
 * */
public class CartaoRepositoryImpl implements CartaoRepository {

    private final CartaoMongoRepository cartaoMongoRepository;

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
            throw new CartaoDuplicadoException("Já existe um cartão cadastrado com este número");
        }

        return cartao;
    }
}
