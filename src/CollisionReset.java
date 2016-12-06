
//Returns the agents initial state effectivly hard reseting to the starting line
public class CollisionReset implements ICollisionHandler {

    @Override
    public State handleCollision(State startState, Position prevPos) {
        return startState;
    }
}
