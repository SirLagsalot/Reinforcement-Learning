
//Interface to facilitate multiple means of handling collisions
public interface ICollisionHandler {

    public State handleCollision(State startState, Position collisionPosition);
}
