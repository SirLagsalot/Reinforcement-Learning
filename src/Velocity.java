
import javafx.util.Pair;


//Holds a set of actions, because calculating them from mins and maxs blows
public class Velocity {
    
    private final Pair<Integer, Integer> velocity;
    
    public Velocity(int xA, int yA) {
        this.velocity = new Pair(xA, yA);
    }
    
    public Pair<Integer, Integer> getVelocity() {
        return velocity;
    }
}
