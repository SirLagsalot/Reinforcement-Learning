
import javafx.util.Pair;

public class Action {

    private final Pair<Integer, Integer> action;

    public Action(int Ax, int Ay) {

        assert (Ax >= -1 && Ax <= 1) : "Invalid X acceleration.";
        assert (Ay >= -1 && Ay <= 1) : "Invalid Y acceleration.";
        this.action = new Pair(Ax, Ay);
    }

    public Pair<Integer, Integer> getAction() {
        return action;
    }
}
