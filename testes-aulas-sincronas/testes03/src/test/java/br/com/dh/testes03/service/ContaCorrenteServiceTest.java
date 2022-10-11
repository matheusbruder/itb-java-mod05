package br.com.dh.testes03.service;

import br.com.dh.testes03.dao.ContaDAO;
import br.com.dh.testes03.exception.ContaInexistenteException;
import br.com.dh.testes03.exception.InvalidNumberException;
import br.com.dh.testes03.modelo.ContaCorrente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContaCorrenteServiceTest {

    @InjectMocks
    private ContaCorrenteService service;

    @Mock
    private ContaDAO dao;

    private ContaCorrente contaCorrente;

    @BeforeEach
    void setup() {
        contaCorrente = new ContaCorrente(1, "Cliente 1");
    }


    @Test
    @DisplayName("Valida nova conta")
    void novaContaCorrente_retornaNovaContaCorrente_quandoSucesso() {
        // Arrange

        Mockito.when(dao.novaContaCorrente(ArgumentMatchers.anyString())).thenReturn(contaCorrente);

        // Act
        ContaCorrente cc = service.novaContaCorrente(contaCorrente.getCliente());

        // Assert
        assertThat(cc).isNotNull();
        assertThat(cc.getNumero()).isPositive();
        assertThat(cc.getCliente()).isEqualTo(contaCorrente.getCliente());
    }

    @Test
    void getConta_retornaContaCorrente_quandoContaExiste() throws ContaInexistenteException {
        // Arrange

        Mockito.when(dao.getContaCorrente(ArgumentMatchers.anyInt())).thenReturn(contaCorrente);

        // Act
        ContaCorrente cc = service.getConta(contaCorrente.getNumero());

        // Assert
        assertThat(cc).isNotNull();
        assertThat(cc.getNumero()).isEqualTo(contaCorrente.getNumero());
        assertThat(cc.getCliente()).isEqualTo(contaCorrente.getCliente());
        assertThat(cc.getSaldo()).isZero();
    }

    @Test
    void getConta_lancaExcecaoContaInexistenteException_quandoContaNaoExiste() throws ContaInexistenteException {
        // Arrange
        int numeroContaInexistente = 999;

        // Act
        BDDMockito.given(dao.getContaCorrente(ArgumentMatchers.anyInt())).willThrow(new ContaInexistenteException("Conta não existe"));

        // Assert
        assertThrows(ContaInexistenteException.class, () -> service.getConta(numeroContaInexistente));
    }

    @Test
    void sacar_retornaVerdadeiro_quandoContaExisteEValorValidoESaldoSuficiente() throws InvalidNumberException, ContaInexistenteException {
        // setup

        double valorOperacao = 100.0;

        contaCorrente.depositar(valorOperacao);

        // Mockar getConta e updateConta
        Mockito.when(dao.getContaCorrente(ArgumentMatchers.anyInt())).thenReturn(contaCorrente);
        Mockito.when(dao.updateConta(ArgumentMatchers.any())).thenReturn(true);

        // run
        boolean success = service.sacar(contaCorrente.getNumero(), valorOperacao);

        // validate
        assertThat(success).isTrue();
        assertThat(contaCorrente.getSaldo()).isZero();
    }

    @Test
    void sacar_LancaExcecaoContaInexistenteException_quandoNumeroInvalido() throws ContaInexistenteException {
        // Arrange
        int numeroContaInexistente = 999;
        double valorOperacao = 100.0;

        // Act
        BDDMockito.given(dao.getContaCorrente(ArgumentMatchers.anyInt())).willThrow(new ContaInexistenteException("Conta não existe"));

        // Assert
        assertThrows(ContaInexistenteException.class, () -> service.getConta(numeroContaInexistente));
        verify(dao, never()).updateConta(ArgumentMatchers.any());
    }

    @Test
    void sacar_LancaExcecaoInvalidNumberException_quandoValorOperacaoInvalido() throws ContaInexistenteException {
        // Arrange
        double valorOperacao = -100.0;

        // Act
        Mockito.when(dao.getContaCorrente(ArgumentMatchers.anyInt())).thenReturn(contaCorrente);

        // Assert
        assertThrows(InvalidNumberException.class, () -> service.sacar(contaCorrente.getNumero(), valorOperacao));
        verify(dao, never()).updateConta(ArgumentMatchers.any());
    }

    @Test
    void sacar_LancaExcecao_quandoSaldoInsuficiente() {
        // Arrange
        // Act
        // Assert
    }

    @Test
    void depositar() {
        // Arrange
        // Act
        // Assert
    }
}