import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Bomba extends Objeto {
private final ImageIcon icone;
    
   
   
    public Bomba(int x, int y) {
        super(x, y);
        this.icone = new ImageIcon(getClass().getResource("bomba-icone.png"));
    }
    public ImageIcon getIcone(){
        return icone;
    }
        
}
