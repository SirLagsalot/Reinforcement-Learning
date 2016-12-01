
public class Agent {

    protected final ILearner learner;
    protected final Policy policy;
    protected State state;

    public Agent(ILearner learner, Policy policy, int startX, int startY) {
        this.learner = learner;
        this.policy = policy;
        state.x = startX;
        state.y = startY;
    }
}
