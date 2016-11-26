
public class Agent {

    protected final Learner learner;
    protected final Policy policy;
    protected State state;

    public Agent(Learner learner, Policy policy, int startX, int startY) {
        this.learner = learner;
        this.policy = policy;
        state.x = startX;
        state.y = startY;
    }
}
