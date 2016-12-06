//Returns to agents previous position with velocity set to 0

public class CollisionStop implements ICollisionHandler {

    @Override
    public State handleCollision(State startState, Position prevPos) {

        return new State(prevPos, new Velocity(0,0));   //im not sure, but i think we just want to set the agent back to its previous spot and 0 its velocity?  This still isnt quite how we want to do this since we dont want to instantiate new state, but should work for now? maybe
//        State state = new State();
//        double shortest = 9999;
//        for (int i = -1; i < 2; i++) {
//            for (int j = -1; j < 2; j++) {
//                if (j != 0 && i != 0) {
//                    double temp = distance(startState.position.x, startState.position.y, prevPos.x + j, prevPos.y + i);
//                    if (temp < shortest) {
//                        shortest = temp;
//                    }
//                }
//            }
//        }
//        return state;
    }

    private double distance(int x0, int y0, int x1, int y1) {
        return Math.sqrt((Math.pow((double) (x1 - x0), 2)) + (Math.pow((double) (y1 - y0), 2)));
    }
}
