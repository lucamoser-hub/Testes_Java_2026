import org.example.CalculadoraFrete;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraFreteTest {

    // Delta de precisão para comparação de valores do tipo double
    private static final double DELTA = 0.0001;

    /**
     * Testa o cálculo de frete para diversas combinações de peso e modalidade.
     * Inclui frete comum e expresso para o mesmo peso (ex: 5.0kg) e o valor de fronteira (0.01kg).
     */
    @ParameterizedTest(name = "Peso: {0}kg | Expresso: {1} -> Esperado: R$ {2}")
    @CsvSource({
            "0.01, false, 8.02",    // Fronteira mínima válida (comum): 8.0 + (0.01 * 2) = 8.02
            "0.01, true,  12.03",   // Fronteira mínima válida (expresso): 8.02 * 1.5 = 12.03
            "5.0,  false, 18.0",    // Peso médio (comum): 8.0 + (5.0 * 2) = 18.0
            "5.0,  true,  27.0",    // Mesmo peso (expresso): 18.0 * 1.5 = 27.0
            "10.0, false, 28.0",    // Peso elevado (comum): 8.0 + (10.0 * 2) = 28.0
            "10.0, true,  42.0"     // Peso elevado (expresso): 28.0 * 1.5 = 42.0
    })
    @DisplayName("Deve calcular o valor do frete corretamente para diferentes entradas válidas")
    void deveCalcularFreteComSucesso(double pesoKg, boolean entregaExpressa, double valorEsperado) {
        double valorObtido = CalculadoraFrete.calcular(pesoKg, entregaExpressa);
        assertEquals(valorEsperado, valorObtido, DELTA, "O valor do frete calculado diverge do esperado.");
    }

    /**
     * Valida o lançamento da exceção IllegalArgumentException e a mensagem exata
     * para valores de peso inválidos (zero e negativos).
     */
    @ParameterizedTest(name = "Peso inválido testado: {0}kg")
    @ValueSource(doubles = {0.0, -0.01, -1.0, -10.5})
    @DisplayName("Deve lançar exceção ao informar peso menor ou igual a zero")
    void deveLancarExcecaoParaPesosInvalidos(double pesoInvalido) {
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> CalculadoraFrete.calcular(pesoInvalido, false),
                "Deveria ter lançado IllegalArgumentException para peso " + pesoInvalido
        );

        assertEquals("O peso deve ser maior que zero.", excecao.getMessage(), "A mensagem da exceção diverge do padrão.");
    }
}