import javax.swing.ImageIcon;

public abstract class Objeto {
    protected int x;
    protected int y;
    protected ImageIcon icone;

    public Objeto(int x, int y, String iconePath) {
        this.x = x;
        this.y = y;
        this.icone = new ImageIcon(getClass().getResource(iconePath));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ImageIcon getIcone() {
        return icone;
    }

    public abstract int getPontos();
    public abstract String interagir();
}