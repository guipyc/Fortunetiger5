package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.Jogador;
import model.JogadorManager;
import model.JogoFortuneTiger;
import view.JogoView;

public class JogoController {
    private JogadorManager jogadorManager;
    private JogoView view;
    private JogoFortuneTiger jogo;
    private File usuariosFile;

    public JogoController(JogadorManager jogadorManager, JogoView view, JogoFortuneTiger jogo) {
        this.jogadorManager = jogadorManager;
        this.view = view;
        this.jogo = jogo;
        this.usuariosFile = new File("usuarios.txt");
        criarArquivoUsuarios();
    }

    private void criarArquivoUsuarios() {
        try {
            if (usuariosFile.createNewFile()) {
                view.mostrarMensagem("Arquivo de usuários criado com sucesso.");
            } else {
                view.mostrarMensagem("Arquivo de usuários já existe.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            view.mostrarMensagem("Erro ao criar o arquivo de usuários.");
        }
    }

    public void iniciar() {
        view.mostrarMensagem("Bem-vindo ao Fortune Tiger!");
        // Exemplo de lógica inicial
        String nome = view.lerEntrada("Digite seu nome: ");
        String senha = view.lerEntrada("Digite sua senha: "); // Pedindo a senha
        Jogador jogador = new Jogador(nome, 1000, senha);  // Supondo saldo inicial de 1000
        jogadorManager.adicionarJogador(jogador);
        view.mostrarMensagem("Jogador " + nome + " adicionado com saldo inicial de 1000.");
        // Outras interações e lógica de controle
        menuPrincipal();
    }

    private void menuPrincipal() {
        boolean sair = false;
        do {
            int opcao = view.mostrarMenuPrincipal();
            switch (opcao) {
                case 1:
                    criarNovoJogador();
                    break;
                case 2:
                    removerJogador();
                    break;
                case 3:
                    buscarJogador();
                    break;
                case 4:
                    listarJogadores();
                    break;
                case 5:
                    fazerLogin();
                    break;
                case 6:
                    fazerLogout();
                    break;
                case 7:
                    girar();
                    break;
                case 8:
                    verSaldo();
                    break;
                case 9:
                    depositar();
                    break;
                case 10:
                    sair = true;
                    break;
                default:
                    view.mostrarMensagem("Opção inválida. Tente novamente.");
            }
        } while (!sair);
        view.mostrarMensagem("Obrigado por jogar!");
    }

    private void criarNovoJogador() {
        String nome = view.lerEntrada("Digite o nome do novo jogador: ");
        String senha = view.lerEntrada("Digite a senha do novo jogador: ");
        Jogador novoJogador = new Jogador(nome, 0, senha); // saldo inicial zero
        jogadorManager.adicionarJogador(novoJogador);
        view.mostrarMensagem("Novo jogador " + nome + " criado com sucesso.");
    }

    private void removerJogador() {
        String nome = view.lerEntrada("Digite o nome do jogador a ser removido: ");
        if (jogadorManager.removerJogador(nome)) {
            view.mostrarMensagem("Jogador " + nome + " removido com sucesso.");
        } else {
            view.mostrarMensagem("Jogador não encontrado.");
        }
    }

    private void buscarJogador() {
        String nome = view.lerEntrada("Digite o nome do jogador a ser buscado: ");
        Jogador jogador = jogadorManager.buscarJogador(nome);
        if (jogador != null) {
            view.mostrarMensagem("Jogador encontrado: " + jogador.getNome() + " com saldo de $" + jogador.getSaldo());
        } else {
            view.mostrarMensagem("Jogador não encontrado.");
        }
    }

    private void listarJogadores() {
        view.mostrarMensagem("Lista de jogadores:");
        for (Jogador jogador : jogadorManager.getJogadores()) {
            view.mostrarMensagem(jogador.getNome() + ": Saldo $" + jogador.getSaldo());
        }
    }

    private void fazerLogin() {
        String nome = view.lerEntrada("Digite seu nome de usuário: ");
        Jogador jogador = jogadorManager.buscarJogador(nome);
        if (jogador != null) {
            String senha = view.lerEntrada("Digite sua senha: ");
            if (jogador.getSenha() != null && jogador.verificarSenha(senha)) {
                view.mostrarMensagem("Login bem-sucedido.");
                jogo.fazerLogin(jogador);
            } else {
                view.mostrarMensagem("Senha incorreta ou não definida.");
            }
        } else {
            view.mostrarMensagem("Usuário não encontrado.");
        }
    }

    private void fazerLogout() {
        jogo.fazerLogout();
        view.mostrarMensagem("Logout realizado com sucesso.");
    }

    private void girar() {
        if (jogo.isJogadorLogado()) {
            jogo.girar();
        } else {


            view.mostrarMensagem("Faça login para jogar.");
        }
    }
    private void verSaldo() {
      if (jogo.isJogadorLogado()) {
          Jogador jogador = jogo.getJogadorLogado();
          view.mostrarMensagem("Saldo atual: $" + jogador.getSaldo());
      } else {
          view.mostrarMensagem("Faça login para ver o saldo.");
      }
  }

  private void depositar() {
      if (jogo.isJogadorLogado()) {
          double valor = view.lerDouble("Digite o valor a ser depositado: ");
          Jogador jogador = jogo.getJogadorLogado();
          jogador.depositar((int) valor); // Convertendo o valor double para int
          view.mostrarMensagem("Depósito de $" + valor + " realizado com sucesso. Novo saldo: $" + jogador.getSaldo());
      } else {
          view.mostrarMensagem("Faça login para depositar.");
      }
  }
}
