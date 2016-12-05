
//Returns to agents previous position with velocity set to 0
public class CollisionStop implements ICollisionHandler {

    @Override
    public State handleCollision(State startState, Position collisionPosition) {
        State state = new State();
        //TODO, find prev square from startState and collisionPosition and return it with Velocity 0;
        return state;
    }
}
