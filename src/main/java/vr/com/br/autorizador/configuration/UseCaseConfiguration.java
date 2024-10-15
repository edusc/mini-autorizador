package vr.com.br.autorizador.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vr.com.br.autorizador.core.domain.repository.CartaoRepository;
import vr.com.br.autorizador.core.usecase.CadastrarCartaoUseCase;
import vr.com.br.autorizador.core.usecase.CadastrarCartaoUseCaseImpl;
import vr.com.br.autorizador.core.usecase.ObterSaldoCartaoUseCase;
import vr.com.br.autorizador.core.usecase.ObterSaldoCartaoUseCaseImpl;

/**
 * Classe responsável por fornecer Beans injetados na camada de user case
 * @author Eduardo Costa
 * */
@Configuration
public class UseCaseConfiguration {

    @Bean
    public CadastrarCartaoUseCase cadastrarCartaoUseCase(CartaoRepository cartaoRepository) {
        return new CadastrarCartaoUseCaseImpl(cartaoRepository);
    }

    @Bean
    public ObterSaldoCartaoUseCase obterSaldoCartaoUseCase(CartaoRepository cartaoRepository) {
        return new ObterSaldoCartaoUseCaseImpl(cartaoRepository);
    }
}
