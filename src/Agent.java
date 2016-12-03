
public class Agent {

    protected final PolicyMaker learner;
    protected final Policy policy;
    protected State state;

    public Agent(PolicyMaker learner, Policy policy, int startX, int startY) {
        this.learner = learner;
        this.policy = policy;
        state.x = startX;
        state.y = startY;
    }
}
