package org.example;

public class CalculadoraFrete {

    // Construtor privado para evitar instanciação de classe utilitária
    private CalculadoraFrete() {
        throw new UnsupportedOperationException("Classe utilitária não deve ser instanciada.");
    }

    public static double calcular(double pesoKg, boolean entregaExpressa) {
        if (pesoKg <= 0) {
            throw new IllegalArgumentException("O peso deve ser maior que zero.");
        }

        double valorBase = 8.0 + (pesoKg * 2.0);

        if (entregaExpressa) {
            return valorBase * 1.5;
        }

        return valorBase;
    }
}