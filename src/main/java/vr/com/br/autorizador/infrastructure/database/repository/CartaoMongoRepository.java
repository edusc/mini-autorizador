package vr.com.br.autorizador.infrastructure.database.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vr.com.br.autorizador.infrastructure.database.model.CartaoModel;

public interface CartaoMongoRepository extends MongoRepository<CartaoModel, String> {
}
