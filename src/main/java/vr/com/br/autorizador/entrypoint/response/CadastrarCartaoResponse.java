package vr.com.br.autorizador.entrypoint.response;


import lombok.Builder;
import lombok.Data;
import vr.com.br.autorizador.core.domain.Cartao;

/**
 * Classe para armazenar os dados da response para cadastro de cartão
 * @author Eduardo Costa
 * */
@Data
@Builder
public class CadastrarCartaoResponse {
    private String numeroCartao;
    private String senha;

    /**
     * Converte um objeto de negócio em um objeto response para um objeto de response
     * @param cartao = Objeto de negócio a ser convertido
     * @return CadastrarCartaoResponse
     * */
    public static CadastrarCartaoResponse toResponse(Cartao cartao) {
        return CadastrarCartaoResponse.builder()
                .numeroCartao(cartao.getNumeroCartao())
                .senha(cartao.getSenha())
                .build();
    }
}


