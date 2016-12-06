
//Returns the agents initial state effectivly hard reseting to the starting line
public class CollisionReset implements ICollisionHandler {

    @Override
    public State handleCollision(State startState, Position collisionPosition) {
        return new State(startState.position, 0, 0);
    }
}
