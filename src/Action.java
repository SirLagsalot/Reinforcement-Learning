
//The Action class stores a pair of values which constitute an acceleration
public class Action {

    public static final Action[] VALID_ACTIONS;     //An array of all possible acceleration, can be used to iterate over potential values

    static {
        VALID_ACTIONS = new Action[9];
        VALID_ACTIONS[0] = (new Action(-1, -1));
        VALID_ACTIONS[1] = (new Action(-1, 0));
        VALID_ACTIONS[2] = (new Action(-1, 1));

        VALID_ACTIONS[3] = (new Action(0, -1));
        VALID_ACTIONS[4] = (new Action(0, 0));
        VALID_ACTIONS[5] = (new Action(0, 1));

        VALID_ACTIONS[6] = (new Action(1, -1));
        VALID_ACTIONS[7] = (new Action(1, 0));
        VALID_ACTIONS[8] = (new Action(1, 1));
    }

    public final int x;
    public final int y;

    public Action(int action) {
        x = ((int) (action / 3)) - 1;
        y = (action % 3) - 1;
    }

    public Action(int Ax, int Ay) {

        assert (Ax >= -1 && Ax <= 1) : "Invalid X acceleration.";
        assert (Ay >= -1 && Ay <= 1) : "Invalid Y acceleration.";

        this.x = Ax;
        this.y = Ay;
    }
}
