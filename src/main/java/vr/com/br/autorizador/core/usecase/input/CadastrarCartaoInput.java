package vr.com.br.autorizador.core.usecase.input;

import lombok.Builder;
import lombok.Data;

/**
 * Classe input para o caso de uso de cadastrar cartoes
 * @author Eduardo Costa
 * */
@Data
@Builder
public class CadastrarCartaoInput {
    private String numeroCartao;
    private String senha;
}
