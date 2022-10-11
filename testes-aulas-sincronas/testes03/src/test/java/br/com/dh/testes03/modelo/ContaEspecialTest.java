package br.com.dh.testes03.modelo;

import br.com.dh.testes03.exception.InvalidNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ContaEspecialTest {

    private ContaEspecial contaEspecial;

    @BeforeEach
    void setup() {
        contaEspecial = new ContaEspecial(2, "Cliente 2", 100);
    }

    @Test
    void sacar_returnTrue_quandoSaldoSuficiente() throws InvalidNumberException {
        double valorDeposito = 100;
        double valorSaque = 50;

        contaEspecial.depositar(valorDeposito);
        contaEspecial.sacar(valorSaque);

        assertThat(contaEspecial.getSaldo()).isEqualTo(valorDeposito - valorSaque);
    }

    @Test
    void sacar_returnFalse_quandoSaldoInsuficiente() throws InvalidNumberException {
        double valorDeposito = 50;
        double valorSaque = 100;

        contaEspecial.depositar(valorDeposito);
        contaEspecial.sacar(valorSaque);

        assertThat(contaEspecial.getSaldo()).isNegative();
    }
    @Test
    void testToString() {
    }
}