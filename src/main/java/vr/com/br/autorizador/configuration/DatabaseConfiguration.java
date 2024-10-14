package vr.com.br.autorizador.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import vr.com.br.autorizador.core.domain.repository.CartaoRepository;
import vr.com.br.autorizador.infrastructure.database.repository.CartaoMongoRepository;
import vr.com.br.autorizador.infrastructure.database.service.CartaoRepositoryImpl;

/**
 * Classe responsável por fornecer beans injetados parq a camada de repository de
 * infrastructure de comunincação com a base de dados
 *
 * @author Eduardo Costa
 * */
@Configuration
@EnableMongoRepositories(basePackages = "vr.com.br.autorizador.infrastructure.database.repository")
public class DatabaseConfiguration {

    @Bean
    public CartaoRepository cartaoRepository (CartaoMongoRepository cartaoMongoRepository) {
        return new CartaoRepositoryImpl(cartaoMongoRepository);
    }
}
