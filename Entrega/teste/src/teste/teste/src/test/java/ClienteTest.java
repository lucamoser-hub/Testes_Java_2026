import org.example.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    private Cliente cliente;

    // Executa ANTES de cada @Test, garantindo isolamento entre os testes (estado limpo)
    @BeforeEach
    void setUp() {
        cliente = new Cliente("Ana Souza", "ana@exemplo.com");
    }

    @Test
    void deveCriarClienteComEstadoInicialValidoEAtivo() {
        // Verifica se o BeforeEach funcionou e o cliente nasceu ativo e com os dados certos
        assertTrue(cliente.isAtivo(), "O cliente deveria nascer ativo.");
        assertEquals("Ana Souza", cliente.getNome());
        assertEquals("ana@exemplo.com", cliente.getEmail());
    }

    @Test
    void deveDesativarClienteAtivoComSucesso() {
        // Ação
        cliente.desativar();

        // Verificação
        assertFalse(cliente.isAtivo(), "O status do cliente deve ser falso (inativo) após desativar.");
    }

    @Test
    void deveLancarExcecaoAoTentarDesativarClienteJaInativo() {
        // Preparação extra: desativa a primeira vez com sucesso
        cliente.desativar();

        // Ação & Verificação: a segunda tentativa DEVE lançar IllegalStateException
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            cliente.desativar(); // Isso vai falhar na regra de negócio
        });

        // Verifica se a mensagem da exceção é a esperada (boa prática)
        assertEquals("Falha: O cliente já está inativo.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoAoCriarClienteSemNome() {
        // Teste bônus para garantir que a validação do construtor também funciona
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("", "email@teste.com");
        });
    }
}