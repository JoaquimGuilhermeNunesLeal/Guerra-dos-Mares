public class Bomba extends Objeto {

    public Bomba(int x, int y) {
        super(x, y, "bomba-icone.png");
    }

    @Override
    public int getPontos() {
        return -1;
    }

    @Override
    public String interagir() {
        return "Você encontrou uma bomba!";
    }
}