
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

    private final int x;
    private final int y;

    public Velocity(int Vx, int Vy) {

        assert (Vx >= -5 && Vx <= 5) : "Invalid X velocity.";
        assert (Vy >= -5 && Vy <= 5) : "Invalid Y velocity.";

        this.x = Vx;
        this.y = Vy;
    }

    public int[] getVelocity() {
        return new int[]{x, y};
    }
}
