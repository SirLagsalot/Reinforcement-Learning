
//Returns to agents previous position with velocity set to 0
public class CollisionStop implements ICollisionHandler {

    @Override
    public State handleCollision(State startState, Position collisionPosition) {
        State state = new State();
        //TODO, find prev square from startState and collisionPosition and return it with Velocity 0;
        double shortest = 9999;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (j != 0 && i != 0) {
                    double temp = distance(startState.x, startState.y, collisionPosition.x + j, collisionPosition.y + i);
                    if (temp < shortest) {
                        shortest = temp;
                    }
                }
            }
        }

        return state;
    }

    public double distance(int x0, int y0, int x1, int y1) {
        return Math.sqrt((Math.pow((double) (x1 - x0), 2)) + (Math.pow((double) (y1 - y0), 2)));
    }
}
