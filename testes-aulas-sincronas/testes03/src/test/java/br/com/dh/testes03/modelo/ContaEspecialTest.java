package br.com.dh.testes03.modelo;

import br.com.dh.testes03.exception.InvalidNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ContaEspecialTest {

    private ContaEspecial contaEspecial;

    @BeforeEach
    void setup() {
        contaEspecial = new ContaEspecial(2, "Cliente 2", 100);
    }

    @Test
    void sacar_returnTrue_quandoSaldoSuficiente() throws InvalidNumberException {
        double valorDeposito = 100;
        double valorSaque = valorDeposito / 2;

        contaEspecial.depositar(valorDeposito);
        contaEspecial.sacar(valorSaque);

        assertThat(contaEspecial.getSaldo()).isEqualTo(valorDeposito - valorSaque);
    }

    @ParameterizedTest
    @CsvSource({"100,50", "50,50", "200,100", "1000, 1"})
    void sacar2_returnTrue_quandoSaldoSuficiente(double valorDeposito, double valorSaque) throws InvalidNumberException {
        contaEspecial.depositar(valorDeposito);
        contaEspecial.sacar(valorSaque);

        assertThat(contaEspecial.getSaldo()).isEqualTo(valorDeposito - valorSaque);
    }

    @Test
    void sacar_returnFalse_quandoSaldoInsuficiente() throws InvalidNumberException {
        double valorSaque = 100;

        contaEspecial.sacar(valorSaque);

        assertThat(contaEspecial.getSaldo()).isZero();
    }

    @Test
    void testToString() {
    }
}