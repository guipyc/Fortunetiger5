package model;

import java.util.Random;

public class JogoFortuneTiger {
    private Jogador jogadorLogado;
    private static final Simbolo[] SIMBOLOS = Simbolo.values();
    private Random random = new Random();

    public void fazerLogin(Jogador jogador) {
        jogadorLogado = jogador;
    }

    public void fazerLogout() {
        jogadorLogado = null;
    }

    public boolean isJogadorLogado() {
        return jogadorLogado != null;
    }

    public Jogador getJogadorLogado() {
        return jogadorLogado;
    }

    // Lógica do jogo de caça-níqueis
    public void girar() {
        if (!isJogadorLogado()) {
            throw new IllegalStateException("Nenhum jogador logado.");
        }

        // Girar os rolos
        Simbolo[][] resultados = new Simbolo[3][3];
        for (int i = 0; i < resultados.length; i++) {
            for (int j = 0; j < resultados[i].length; j++) {
                resultados[i][j] = SIMBOLOS[random.nextInt(SIMBOLOS.length)];
            }
        }

        // Imprimir os resultados
        System.out.println("Resultado do giro:");
        for (int i = 0; i < resultados.length; i++) {
            for (int j = 0; j < resultados[i].length; j++) {
                System.out.print(resultados[i][j] + " ");
            }
            System.out.println();
        }

        // Verificar prêmios
        Premio premio = verificarPremio(resultados);
        if (premio != Premio.SEM_PREMIO) {
            System.out.println("Parabéns! Você ganhou o prêmio: " + premio);
            switch (premio) {
                case JACKPOT:
                    jogadorLogado.adicionarPremio(100);
                    break;
                case GRANDE_PREMIO:
                    jogadorLogado.adicionarPremio(50);
                    break;
                case PREMIO_MEDIO:
                    jogadorLogado.adicionarPremio(20);
                    break;
                case PREMIO_PEQUENO:
                    jogadorLogado.adicionarPremio(10);
                    break;
                default:
                    break;
            }
            System.out.println("Novo saldo: $" + jogadorLogado.getSaldo());
        } else {
            System.out.println("Infelizmente, você não ganhou nenhum prêmio neste giro.");
        }
    }

    // Verificar os prêmios ganhos
    private Premio verificarPremio(Simbolo[][] resultados) {
      for (int i = 0; i < resultados.length; i++) {
        if (resultados[i][0] == resultados[i][1] && resultados[i][1] == resultados[i][2]) {
            switch (resultados[i][0]) {
                case TIGRINHO:
                    return Premio.JACKPOT;
                case PEIXE:
                    return Premio.GRANDE_PREMIO;
                case ALGA:
                    return Premio.PREMIO_MEDIO;
                case CONCHA:
                    return Premio.PREMIO_PEQUENO;
                default:
                    break;
            }
        }
    }

    // Verificar se todos os símbolos em uma coluna são iguais
    for (int j = 0; j < 3; j++) {
        if (resultados[0][j] == resultados[1][j] && resultados[1][j] == resultados[2][j]) {
            switch (resultados[0][j]) {
                case TIGRINHO:
                    return Premio.JACKPOT;
                case PEIXE:
                    return Premio.GRANDE_PREMIO;
                case ALGA:
                    return Premio.PREMIO_MEDIO;
                case CONCHA:
                    return Premio.PREMIO_PEQUENO;
                default:
                    break;
            }
        }
    }

    // Verificar se há três símbolos iguais na diagonal principal
    if (resultados[0][0] == resultados[1][1] && resultados[1][1] == resultados[2][2]) {
        switch (resultados[0][0]) {
            case TIGRINHO:
                return Premio.JACKPOT;
            case PEIXE:
                return Premio.GRANDE_PREMIO;
            case ALGA:
                return Premio.PREMIO_MEDIO;
            case CONCHA:
                return Premio.PREMIO_PEQUENO;
            default:
                break;
        }
    }

    // Verificar se há três símbolos iguais na diagonal secundária
    if (resultados[0][2] == resultados[1][1] && resultados[1][1] == resultados[2][0]) {
        switch (resultados[0][2]) {
            case TIGRINHO:
                return Premio.JACKPOT;
            case PEIXE:
                return Premio.GRANDE_PREMIO;
            case ALGA:
                return Premio.PREMIO_MEDIO;
            case CONCHA:
                return Premio.PREMIO_PEQUENO;
            default:
                break;
        }
    }

    return Premio.SEM_PREMIO;
}

}
