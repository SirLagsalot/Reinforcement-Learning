
public class CollisionReset implements ICollisionHandler {

    @Override
    public State handleCollision(State startState, Position collisionPosition) {
        State state = new State();
        state.x = startState.x;
        state.y = startState.y;
        state.Vx = 0;
        state.Vy = 0;
        return state;
    }
}
