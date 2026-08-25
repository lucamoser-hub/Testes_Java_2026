package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservaHotel {
    private String nomeHospede;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private int quantidadeHospedes;
    private boolean confirmada;

    // Construtor validado
    public ReservaHotel(String nomeHospede, LocalDate dataCheckIn, LocalDate dataCheckOut, int quantidadeHospedes) {
        if (nomeHospede == null || nomeHospede.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do hóspede não pode ser nulo ou vazio.");
        }
        if (dataCheckOut.isBefore(dataCheckIn) || dataCheckOut.isEqual(dataCheckIn)) {
            throw new IllegalArgumentException("A data de check-out deve ser estritamente posterior à data de check-in.");
        }
        if (quantidadeHospedes <= 0) {
            throw new IllegalArgumentException("A quantidade de hóspedes deve ser maior que zero.");
        }

        this.nomeHospede = nomeHospede;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.quantidadeHospedes = quantidadeHospedes;
        this.confirmada = false; // Toda reserva nasce como não confirmada
    }

    // Método com regra de negócio
    public void confirmar() {
        if (this.confirmada) {
            throw new IllegalStateException("Esta reserva já foi confirmada anteriormente.");
        }
        this.confirmada = true;
    }

    // Método com regra de negócio
    public long calcularDiarias() {
        return ChronoUnit.DAYS.between(dataCheckIn, dataCheckOut);
    }

    // Getters para os testes
    public String getNomeHospede() { return nomeHospede; }
    public LocalDate getDataCheckIn() { return dataCheckIn; }
    public LocalDate getDataCheckOut() { return dataCheckOut; }
    public int getQuantidadeHospedes() { return quantidadeHospedes; }
    public boolean isConfirmada() { return confirmada; }
}