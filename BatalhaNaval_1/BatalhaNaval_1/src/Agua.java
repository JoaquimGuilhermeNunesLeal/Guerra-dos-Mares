import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Agua extends Objeto {


    private final ImageIcon icone;

    public Agua(int x, int y) {
        super(x, y);
         this.icone = new ImageIcon(getClass().getResource("agua-icone.png"));
    }
    
    public ImageIcon getIcone(){
        return icone;
    }
}
