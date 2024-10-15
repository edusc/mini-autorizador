package vr.com.br.autorizador.core.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vr.com.br.autorizador.core.domain.repository.CartaoRepository;
import vr.com.br.autorizador.core.usecase.ObterSaldoCartaoUseCaseImpl;
import vr.com.br.autorizador.entrypoint.exception.CartaoNaoEncontradoException;
import vr.com.br.autorizador.infrastructure.database.model.CartaoModel;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

public class ObterSaldoCartaoUseCaseImplTest {

    @InjectMocks
    private ObterSaldoCartaoUseCaseImpl obterSaldoCartaoUseCase;

    @Mock
    private CartaoRepository cartaoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeveRetornarSaldoCartao() {
        String numeroCartao = "12345678910";

        CartaoModel cartaoModel = CartaoModel.builder()
                .numeroCartao("12345678910")
                .senha("123")
                .saldo(new BigDecimal("500"))
                .build();

        when(cartaoRepository.obterSaldoCartao(numeroCartao)).thenReturn(new BigDecimal("500"));

        BigDecimal saldo = obterSaldoCartaoUseCase.execute(numeroCartao);
        Assertions.assertEquals(cartaoModel.getSaldo(), saldo);

    }

    @Test
    public void testDeveRetornar404CasoCartaoNaoEncontrado() {
        String numeroCartao = "12345678910";

        when(cartaoRepository.obterSaldoCartao(numeroCartao))
                .thenThrow(new CartaoNaoEncontradoException("Cartão nao encontrado"));

        Assertions.assertThrows(CartaoNaoEncontradoException.class, () ->
            obterSaldoCartaoUseCase.execute(numeroCartao)
        );
    }
}
