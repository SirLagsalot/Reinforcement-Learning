
//Returns to agents previous position with velocity set to 0
public class CollisionStop implements ICollisionHandler {

    @Override
    public State handleCollision(State startState, Position prevPos) {
        return new State(prevPos, new Velocity(0, 0));
    }
}
