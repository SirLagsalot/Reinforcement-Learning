
public class Agent {

    protected final Learner learner;
    protected State state;

    public Agent(Learner learner, int startX, int startY) {
        this.learner = learner;
        state.x = startX;
        state.y = startY;
    }
}
