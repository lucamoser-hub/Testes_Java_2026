package org.example;
public class Cliente {

    private String nome;
    private String email;
    private boolean ativo;

    // Construtor que garante um estado válido inicial
    public Cliente(String nome, String email) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode ser vazio.");
        }
        this.nome = nome;
        this.email = email;
        this.ativo = true; // Todo cliente nasce ativo no sistema
    }

    /**
     * Regra de negócio: Só é possível desativar se o cliente estiver ativo.
     */
    public void desativar() {
        if (!this.ativo) {
            throw new IllegalStateException("Falha: O cliente já está inativo.");
        }
        this.ativo = false;
    }

    // Getters para expor os dados de forma segura (sem setters)

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAtivo() {
        return ativo;
    }
}