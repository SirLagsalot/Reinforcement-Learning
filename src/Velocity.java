
import java.util.LinkedList;
import javafx.util.Pair;


//Holds a set of actions, because calculating them from mins and maxs blows
public class Velocity {

    private static final LinkedList<Velocity> VALID_VELOCITES;      //I dont think this can be static if were going to have a unique set for each position

    static {
        VALID_VELOCITES = new LinkedList<>();
        for (int x = -5; x < 6; x++) {
            for (int y = -5; y < 6; y++) {
                VALID_VELOCITES.add(new Velocity(x, y));
            }
        }
    }

    private final Pair<Integer, Integer> velocity;

    public Velocity(int xA, int yA) {
        this.velocity = new Pair(xA, yA);
    }

    public Pair<Integer, Integer> getVelocity() {
        return velocity;
    }
}
