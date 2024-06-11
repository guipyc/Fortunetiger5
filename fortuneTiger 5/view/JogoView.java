package view;

import java.util.Scanner;

public class JogoView {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public String lerEntrada(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // Método para mostrar o menu principal e receber a escolha do usuário
    public int mostrarMenuPrincipal() {
        mostrarMensagem("Menu Principal:");
        mostrarMensagem("1. Criar novo jogador");
        mostrarMensagem("2. Remover jogador");
        mostrarMensagem("3. Buscar jogador");
        mostrarMensagem("4. Listar jogadores");
        mostrarMensagem("5. Fazer login");
        mostrarMensagem("6. Fazer logout");
        mostrarMensagem("7. Girar");
        mostrarMensagem("8. Ver saldo");
        mostrarMensagem("9. Depositar");
        mostrarMensagem("10. Sair");
        String escolha = lerEntrada("Escolha uma opção: ");
        return Integer.parseInt(escolha);
    }

    // Método para ler um valor double fornecido pelo usuário
    public double lerDouble(String prompt) {
        mostrarMensagem(prompt);
        String valor = lerEntrada("");
        return Double.parseDouble(valor);
    }
}
