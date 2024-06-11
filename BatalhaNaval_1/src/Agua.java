import javax.swing.Icon;
public class Agua extends Objeto {

    public Agua(int x, int y) {
        super(x, y, "agua-icone.png");
    }

    @Override
    public int getPontos() {
        return 0;
    }

    @Override
    public String interagir() {
        return "Você não encontrou, somente água.";
    }
}