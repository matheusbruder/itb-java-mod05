package br.com.mpbruder.teste01.calculadora;

public class Calculadora {
    public double somar(double n1, double n2) {
        return n1 + n2;
    }

    public double subtrair(double n1, double n2) {
        return n1 - n2;
    }



    public double dividir(double n1, double n2) {
        if (n2 == 0.0) {
            return 0;
        }
        return n1 / n2;
    }

    public double multiplicar(double n1, double n2) {
        return n1 * n2;
    }
}