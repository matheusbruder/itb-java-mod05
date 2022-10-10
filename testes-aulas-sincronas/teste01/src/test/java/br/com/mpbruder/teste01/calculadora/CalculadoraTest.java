package br.com.mpbruder.teste01.calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CalculadoraTest {

    Calculadora calculadora;

    @BeforeEach
    public void setup(){
        calculadora = new Calculadora();
    }

    /**
     * dado que — quando — então
     * métodos testados — o que é esperado — dados fornecidos
     */
    @Test
    @DisplayName("Validação da soma")
    void somar_returnDouble_whenValidInput() {
        // setup
        double n1 = 10.0;
        double n2 = 20.0;
        double expected = 30.0;

        // run
        double result = calculadora.somar(n1, n2);

        // validate
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Validação divisão positivos")
    void dividir_returnDouble_whenTwoPositiveNumbers() {
        // setup
        double n1 = 30.0;
        double n2 = 3.0;
        double expected = 10.0;

        // run
        double result = calculadora.dividir(n1, n2);

        // validate
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Validação divisão por zero")
    void dividir_returnDouble_whenDivisorEqualsZero() {
        // setup
        double n1 = 100.0;
        double n2 = 0.0;
        double expected = 0.0;

        // run
        double result = calculadora.dividir(n1, n2);

        // validate
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Validação subtração")
    void subtrair_returnDouble_whenTwoPositiveNumbers() {
        // setup
        double n1 = 10.0;
        double n2 = 10.0;
        double expected = 0.0;

        // run
        double result = calculadora.subtrair(n1, n2);

        // validate
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Validação multiplicação")
    void multiplicar_returnDouble_whenTwoPositiveNumbers() {
        // setup
        double n1 = 10.0;
        double n2 = -10.0;
        double expected = -100.0;

        // run
        double result = calculadora.multiplicar(n1, n2);

        // validate
        assertEquals(expected, result);
    }
}