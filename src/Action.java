
import java.util.Collection;
import java.util.LinkedList;
import javafx.util.Pair;

public class Action {

    private static final Collection<Action> VALID_ACTIONS;

    static {
        VALID_ACTIONS = new LinkedList<>();
        VALID_ACTIONS.add(new Action(-1, -1));
        VALID_ACTIONS.add(new Action(-1, 0));
        VALID_ACTIONS.add(new Action(-1, 1));

        VALID_ACTIONS.add(new Action(0, -1));
        VALID_ACTIONS.add(new Action(0, 0));
        VALID_ACTIONS.add(new Action(0, 1));

        VALID_ACTIONS.add(new Action(1, -1));
        VALID_ACTIONS.add(new Action(1, 0));
        VALID_ACTIONS.add(new Action(1, 1));
    }
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
