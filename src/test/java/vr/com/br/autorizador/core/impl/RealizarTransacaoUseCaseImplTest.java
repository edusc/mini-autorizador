package vr.com.br.autorizador.core.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vr.com.br.autorizador.core.domain.Cartao;
import vr.com.br.autorizador.core.domain.repository.CartaoRepository;
import vr.com.br.autorizador.core.usecase.RealizarTransacaoUseCaseImpl;
import vr.com.br.autorizador.core.usecase.input.RealizarTransacaoInput;
import vr.com.br.autorizador.entrypoint.exception.CartaoNaoEncontradoException;
import vr.com.br.autorizador.entrypoint.exception.SaldoInsuficienteException;
import vr.com.br.autorizador.entrypoint.exception.SenhaInvalidaException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RealizarTransacaoUseCaseImplTest {

    @InjectMocks
    private RealizarTransacaoUseCaseImpl realizarTransacaoUseCase ;

    @Mock
    private CartaoRepository cartaoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeveLancarExcecaoQuandoCartaoNaoForEncontrado() {
        String numeroCartao = "123456789";

        when(cartaoRepository.obterCartaoPeloNumero(numeroCartao)).thenReturn(Optional.empty());

        RealizarTransacaoInput input = RealizarTransacaoInput.builder()
                .numeroCartao(numeroCartao)
                .senhaCartao("senha")
                .valor(BigDecimal.TEN)
                .build();

        assertThrows(CartaoNaoEncontradoException.class, () -> realizarTransacaoUseCase.execute(input));

        verify(cartaoRepository, times(1)).obterCartaoPeloNumero(numeroCartao);
    }

    @Test
    public void testDeveLancarExcecaoQuandoSenhaForInvalida() {
        String numeroCartao = "123456789";
        Cartao cartao = Cartao.builder()
                .numeroCartao(numeroCartao)
                .senha("senhaCorreta")
                .saldo(new BigDecimal("100"))
                .build();

        when(cartaoRepository.obterCartaoPeloNumero(numeroCartao)).thenReturn(Optional.of(cartao));

        RealizarTransacaoInput input = RealizarTransacaoInput.builder()
                .numeroCartao(numeroCartao)
                .senhaCartao("senhaIncorreta")
                .valor(BigDecimal.TEN)
                .build();

        assertThrows(SenhaInvalidaException.class, () -> realizarTransacaoUseCase.execute(input));
        verify(cartaoRepository, times(1)).obterCartaoPeloNumero(numeroCartao);
    }

    @Test
    public void testDeveLancarExcecaoQuandoSaldoForInsuficiente() {
        String numeroCartao = "123456789";
        Cartao cartao = Cartao.builder()
                .numeroCartao(numeroCartao)
                .senha("senhaCorreta")
                .saldo(new BigDecimal("50"))
                .build();

        when(cartaoRepository.obterCartaoPeloNumero(numeroCartao)).thenReturn(Optional.of(cartao));

        RealizarTransacaoInput input = RealizarTransacaoInput.builder()
                .numeroCartao(numeroCartao)
                .senhaCartao("senhaCorreta")
                .valor(new BigDecimal("100"))
                .build();

        assertThrows(SaldoInsuficienteException.class, () -> realizarTransacaoUseCase.execute(input));
        verify(cartaoRepository, times(1)).obterCartaoPeloNumero(numeroCartao);
    }

    @Test
    public void deveRealizarTransacaoComSucesso() {
        String numeroCartao = "123456789";
        BigDecimal saldoInicial = new BigDecimal("100");
        BigDecimal valorTransacao = new BigDecimal("30");

        Cartao cartao = Cartao.builder()
                .numeroCartao(numeroCartao)
                .senha("senhaCorreta")
                .saldo(saldoInicial)
                .build();

        when(cartaoRepository.obterCartaoPeloNumero(numeroCartao)).thenReturn(Optional.of(cartao));

        RealizarTransacaoInput input = RealizarTransacaoInput.builder()
                .numeroCartao(numeroCartao)
                .senhaCartao("senhaCorreta")
                .valor(valorTransacao)
                .build();

        realizarTransacaoUseCase.execute(input);

        assertEquals(saldoInicial.subtract(valorTransacao), cartao.getSaldo());
        verify(cartaoRepository, times(1)).atualizarCartao(cartao);
    }
}
