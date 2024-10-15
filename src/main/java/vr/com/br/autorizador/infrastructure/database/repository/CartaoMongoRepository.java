package vr.com.br.autorizador.infrastructure.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import vr.com.br.autorizador.infrastructure.database.model.CartaoModel;

import java.util.Optional;


public interface CartaoMongoRepository extends MongoRepository<CartaoModel, String> {
    @Query(value = "{ 'numeroCartao': ?0 }", fields = "{ 'saldo' : 1 }")
    Optional<CartaoModel> findSaldoByNumeroCartao(String numeroCartao);

}
