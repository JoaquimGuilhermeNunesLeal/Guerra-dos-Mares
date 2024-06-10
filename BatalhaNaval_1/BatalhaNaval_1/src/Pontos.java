public class Pontos {
    private int pontos;
    private int chances;

    public Pontos() {
        this.pontos = 0;
        this.chances = 10;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    public void decrementarChances() {
        this.chances--;
    }

    public int getPontos() {
        return pontos;
    }

    public int getChances() {
        return chances;
    }

    public boolean jogoTerminado() {
        return chances == 0 || pontos == 3 || pontos == -3;
    }

    public boolean jogadorGanhou() {
        return pontos == 3;
    }
}

