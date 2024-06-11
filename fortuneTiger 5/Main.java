import model.JogadorManager;
import view.JogoView;
import controller.JogoController;
import model.JogoFortuneTiger;

public class Main {
    public static void main(String[] args) {
        JogadorManager jogadorManager = new JogadorManager();
        JogoView view = new JogoView();
        JogoFortuneTiger jogo = new JogoFortuneTiger();
        JogoController controller = new JogoController(jogadorManager, view, jogo);
        
        controller.iniciar();
    }
}
