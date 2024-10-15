package vr.com.br.autorizador.core.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vr.com.br.autorizador.core.domain.Cartao;
import vr.com.br.autorizador.core.domain.repository.CartaoRepository;
import vr.com.br.autorizador.core.usecase.CadastrarCartaoUseCaseImpl;
import vr.com.br.autorizador.core.usecase.input.CadastrarCartaoInput;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CadastrarCartaoUseCaseImplTest {

    @InjectMocks
    private CadastrarCartaoUseCaseImpl cadastrarCartaoUseCase;

    @Mock
    private CartaoRepository cartaoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeveInserirCartaoCorretamente() {
        CadastrarCartaoInput input = CadastrarCartaoInput.builder()
                .numeroCartao("12345678910")
                .senha("1234")
                .build();

        Cartao cartaoEsperado = Cartao.builder()
                .numeroCartao(input.getNumeroCartao())
                .senha(input.getSenha())
                .saldo(new BigDecimal("500"))
                .build();

        Cartao cartaoResultante = cadastrarCartaoUseCase.execute(input);

        assertEquals(cartaoEsperado.getNumeroCartao(), cartaoResultante.getNumeroCartao());
        assertEquals(cartaoEsperado.getSenha(), cartaoResultante.getSenha());
        assertEquals(cartaoEsperado.getSaldo(), cartaoResultante.getSaldo());

        verify(cartaoRepository, times(1)).salvarCartao(cartaoResultante);

    }

}
