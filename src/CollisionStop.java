
//Returns to agents previous position with velocity set to 0
public class CollisionStop implements ICollisionHandler {

    @Override
    public State handleCollision(State startState, Position collisionPosition) {
        //TODO, find prev square from startState and collisionPosition and return it with Velocity 0;
        
        return new State(collisionPosition, 0, 0);
    }

    public double distance(int x0, int y0, int x1, int y1) {
        return Math.sqrt((Math.pow((double) (x1 - x0), 2)) + (Math.pow((double) (y1 - y0), 2)));
    }
}
