package vr.com.br.autorizador.core.usecase;

import vr.com.br.autorizador.core.domain.Cartao;
import vr.com.br.autorizador.core.usecase.input.CadastrarCartaoInput;

/**
 * Interface que define o comportamento do caso de uso de cadastrar cartoes
 * @author Eduardo Costa
 * */
public interface CadastrarCartaoUseCase {

    /**
     * Realiza o cadastro do cartão no banco de dados da aplicação
     * @param input - dados do cartao
     * @return Cartao
     *
     * */
    Cartao execute(CadastrarCartaoInput input);
}
