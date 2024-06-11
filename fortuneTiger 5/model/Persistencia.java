package model;

import java.io.*;
import java.util.List;

public class Persistencia {
    public void salvarDados(List<Jogador> jogadores, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(jogadores);
        }
    }

    public List<Jogador> carregarDados(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Jogador>) in.readObject();
        }
    }
}
