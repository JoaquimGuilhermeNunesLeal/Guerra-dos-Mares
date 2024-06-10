
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Navio extends Objeto {


    private final ImageIcon icone;
 
    public Navio(int x, int y) {
        super(x, y);
         this.icone = new ImageIcon(getClass().getResource("navio-icone.png"));
    }
    
    public ImageIcon getIcone(){
        return icone;
    }
}
