package org.example;

public class Medicamento {

    // Atributos privados garantem que o estado interno não seja alterado de fora
    private String nome;
    private double preco;
    private int quantidadeEstoque;
    private boolean exigeReceita;

    /**
     * Construtor garante que o objeto já "nasça" em um estado válido.
     * Não faz sentido criar um remédio sem nome ou com preço negativo.
     */
    public Medicamento(String nome, double preco, boolean exigeReceita) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do medicamento não pode ser vazio.");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero.");
        }

        this.nome = nome;
        this.preco = preco;
        this.exigeReceita = exigeReceita;
        this.quantidadeEstoque = 0; // Todo medicamento nasce com estoque zero até que chegue um lote
    }

    /**
     * Regra de Negócio 1: Só é possível dar entrada em quantidades positivas no estoque.
     */
    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade a ser adicionada deve ser maior que zero.");
        }
        this.quantidadeEstoque += quantidade;
    }

    /**
     * Regra de Negócio 2 e 3: Venda exige estoque suficiente e, caso aplicável, retenção de receita.
     */
    public void vender(int quantidade, boolean clienteApresentouReceita) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de venda deve ser maior que zero.");
        }
        if (quantidade > this.quantidadeEstoque) {
            throw new IllegalStateException("Estoque insuficiente para a venda.");
        }
        if (this.exigeReceita && !clienteApresentouReceita) {
            throw new IllegalStateException("Venda bloqueada: este medicamento exige apresentação de receita médica.");
        }

        this.quantidadeEstoque -= quantidade;
    }

    // Apenas Getters. Sem Setters!
    // O estoque só muda pelas regras de negócio (adicionarEstoque e vender).
    // O preço e o nome são imutáveis após a criação neste modelo (ou exigiriam métodos específicos de reajuste).

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public boolean isExigeReceita() {
        return exigeReceita;
    }
}