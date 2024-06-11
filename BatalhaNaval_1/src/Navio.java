public class Navio extends Objeto {

    public Navio(int x, int y) {
        super(x, y, "navio-icone.png");
    }

    @Override
    public int getPontos() {
        return 1;
    }

    @Override
    public String interagir() {
        return "Você encontrou um navio!";
    }
}