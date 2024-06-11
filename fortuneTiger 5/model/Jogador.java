package model;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private int saldo;
    private String senha;
    private List<Integer> premios = new ArrayList<>(); // Lista para armazenar os prêmios do jogador

    public Jogador(String nome, int saldo, String senha) {
        this.nome = nome;
        this.saldo = saldo;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public void depositar(int valor) {
        saldo += valor;
    }

    public void adicionarPremio(int premio) {
        premios.add(premio);
        saldo += premio; // Atualiza o saldo com o valor do prêmio
    }

    // Outros métodos da classe Jogador
}
