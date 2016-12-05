
public class CollisionReset implements ICollisionHandler {

    @Override
    public State handleCollision(State startState, Position collisionPosition) {
        return new State(startState.position, 0, 0);
    }
}
