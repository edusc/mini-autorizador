package vr.com.br.autorizador.entrypoint.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Classe para armazenar os dados da request
 * @author Eduardo Costa
 * */
@Data
public class CadastrarCartaoRequest {

    @NotNull(message = "Número do cartão é obrigatório.")
    private String numeroCartao;
    @NotNull(message = "Senha do cartão é obrigatória")
    private String senha;

}
