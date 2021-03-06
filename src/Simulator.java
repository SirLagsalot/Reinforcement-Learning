
public class Simulator {

    public final char[][] track;

    private final ICollisionHandler collisionHandler;
    private State startState, currentState;
    private int collisions;

    public Simulator(char[][] track, ICollisionHandler collisionHandler) {

        startState = getStartState(track);//this  gets the first start state possible
        this.collisionHandler = collisionHandler;
        this.track = track;
    }
    
    //Sets the start state, only used by collision reset
    public void setStartState(State state){
        startState = new State(state.position, state.velocity);
    }
    
    //Returns the first available start state, this is what we train on
    private State getStartState(char[][] track) {

        for (int row = 0; row < track.length; row++) {
            for (int col = 0; col < track[row].length; col++) {
                if (track[row][col] == 'S') {
                    return new State(col, row, 0, 0);
                }
            }
        }
        return new State();
    }

    //Performs an action with no chance of failure
    public State takeActionDetermisistic(State state, Action action) {
        state.velocity.changeVelocity(action);
        currentState = traverse(state);
        return currentState;
    }

    //Performs an action with 20% chance of failure
    public State takeAction(State state, Action action) {

        state.velocity.accelerate(action);
        currentState = traverse(state);
        return currentState;
    }

    //Moves the agent according to their current velocity and detects wall collisions using
    //a super cover implementaion of Bresenham's line algorithm
    private State traverse(State state) {

        int x = state.position.x;
        int y = state.position.y;
        int vx = state.velocity.x;
        int vy = state.velocity.y;
        int x2 = x + vx;
        int y2 = y + vy;
        int count = 1 + Math.abs(vx) + Math.abs(vy);
        int xInc = (x2 > x) ? 1 : (x2 == x) ? 0 : -1;
        int yInc = (y2 > y) ? 1 : (y2 == y) ? 0 : -1;
        int error = Math.abs(vx) - Math.abs(vy);

        int prevX = x;
        int prevY = y;
        vx = vx << 1;
        vy = vy << 1;

        for (; count > 0; --count) {

            if (track[y][x] == '#') {
                collisions++;
                return collisionHandler.handleCollision(startState, new Position(prevX, prevY));
            } else if (track[y][x] == 'F') {
                state.finish = true;
                state.position = new Position(x, y);
                state.velocity = new Velocity(0, 0);    //final states are always 0 velocity.
                return state;                           //need the final state for other code to know it's the final state...
            }
            if (error > 0) {
                prevX = x;
                prevY = y;
                x += xInc;
                error -= Math.abs(vy);
            } else if (error == 0) {
                prevX = x;
                prevY = y;
                x += xInc;
                y += yInc;
                count--;                                //affected both x and y so this was effectively 2 moves.
            } else {
                prevX = x;
                prevY = y;
                y += yInc;
                error += Math.abs(vx);
            }
        }

        if (track[y2][x2] == '#') {
            return collisionHandler.handleCollision(startState, new Position(prevX, prevY));
        }
        if (track[y2][x2] == 'F') {
            state.velocity = new Velocity(0, 0);
            state.finish = true;
        }
        state.position = new Position(x2, y2);
        return state;
    }

    //Print the racetrack with agent's location to the console
    public void printTrack() {

        int x = currentState.position.x;
        int y = currentState.position.y;

        for (int row = 0; row < track.length; row++) {
            for (int col = 0; col < track[0].length; col++) {
                if (x == col && y == row) {
                    System.out.print("A");
                } else {
                    System.out.print(track[row][col]);
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public int getCollisions() {
        return collisions;
    }
}
