package model;

import java.util.ArrayList;
import java.util.List;

public class JogadorManager {
    private List<Jogador> jogadores = new ArrayList<>();

    public void adicionarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public Jogador buscarJogador(String nome) {
        for (Jogador jogador : jogadores) {
            if (jogador.getNome().equals(nome)) {
                return jogador;
            }
        }
        return null;
    }

    public boolean removerJogador(String nome) {
        Jogador jogador = buscarJogador(nome);
        if (jogador != null) {
            jogadores.remove(jogador);
            return true;
        }
        return false;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }
}
