
public class Agent {

    protected final PolicyMaker learner;
    protected State state;
    protected StateInfo stateInfo;

    public Agent(PolicyMaker learner, int startX, int startY) {
        this.learner = learner;
        state.x = startX;
        state.y = startY;
    }
    
    public int[] createPolicy() {
        return learner.createPolicy();
    }
}
