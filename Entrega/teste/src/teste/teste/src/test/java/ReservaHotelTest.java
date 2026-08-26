import org.example.ReservaHotel;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaHotelTest {

    @Test
    public void deveCalcularQuantidadeDeDiariasCorretamente() {
        // Arrange (Preparar: Configura os dados necessários para o teste)
        LocalDate checkIn = LocalDate.of(2023, 10, 1);
        LocalDate checkOut = LocalDate.of(2023, 10, 6);
        ReservaHotel reserva = new ReservaHotel("João Silva", checkIn, checkOut, 2);

        // Act (Agir: Executa a ação ou método que está sendo testado)
        long totalDiarias = reserva.calcularDiarias();

        // Assert (Verificar: Confirma se o resultado é o esperado usando assertEquals)
        assertEquals(5, totalDiarias, "O cálculo de diárias deve resultar em 5 dias exatos.");
    }

    @Test
    public void deveMudarOStatusDaReservaParaConfirmadaAoChamarMetodoConfirmar() {
        // Arrange
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = checkIn.plusDays(3);
        ReservaHotel reserva = new ReservaHotel("Maria Souza", checkIn, checkOut, 1);

        // Assert prévio (Usando assertFalse para garantir o estado inicial)
        assertFalse(reserva.isConfirmada(), "Uma nova reserva deve nascer com o status não confirmada (false).");

        // Act
        reserva.confirmar();

        // Assert (Usando assertTrue para garantir a mudança de estado)
        assertTrue(reserva.isConfirmada(), "A reserva deve mudar para confirmada (true) após a execução do método.");
    }

    @Test
    public void naoDevePermitirCriacaoDeReservaComCheckOutAnteriorAoCheckIn() {
        // Arrange
        String nome = "Carlos";
        LocalDate checkIn = LocalDate.of(2023, 12, 10);
        LocalDate checkOutInvalido = LocalDate.of(2023, 12, 5); // Check-out no passado
        int hospedes = 2;

        // Act & Assert (Usando assertThrows para validar o cenário de erro)
        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> new ReservaHotel(nome, checkIn, checkOutInvalido, hospedes)
        );

        assertEquals("A data de check-out deve ser estritamente posterior à data de check-in.", excecao.getMessage());
    }

    @Test
    public void deveCriarReservaAtribuindoTodosOsValoresCorretamente() {
        // Arrange
        String nomeEsperado = "Ana Paula";
        LocalDate checkInEsperado = LocalDate.of(2024, 1, 10);
        LocalDate checkOutEsperado = LocalDate.of(2024, 1, 20);
        int hospedesEsperados = 4;

        // Act
        ReservaHotel reserva = new ReservaHotel(nomeEsperado, checkInEsperado, checkOutEsperado, hospedesEsperados);

        // Assert (Usando assertAll para agrupar múltiplas validações)
        // Se uma falhar, as outras ainda são validadas e reportadas no console
        assertAll("Verificação dos atributos da Reserva de Hotel",
                () -> assertEquals(nomeEsperado, reserva.getNomeHospede(), "Nome do hóspede foi salvo incorretamente."),
                () -> assertEquals(checkInEsperado, reserva.getDataCheckIn(), "Data de check-in foi salva incorretamente."),
                () -> assertEquals(checkOutEsperado, reserva.getDataCheckOut(), "Data de check-out foi salva incorretamente."),
                () -> assertEquals(hospedesEsperados, reserva.getQuantidadeHospedes(), "Quantidade de hóspedes foi salva incorretamente.")
        );
    }
}