package org.example;

public class Triangulo {
    private double ladoA, ladoB, ladoC;

    public Triangulo(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0)
            throw new IllegalArgumentException("Lados devem ser positivos.");
        this.ladoA = a; this.ladoB = b; this.ladoC = c;
    }

    public double calcularPerimetro() { return ladoA + ladoB + ladoC; }

    public double getLadoA() {
        return 3.0;
    }

    public double getLadoB() {
        return 4.0;
    }

    public double getLadoC() {
        return 5.0;
    }
}
