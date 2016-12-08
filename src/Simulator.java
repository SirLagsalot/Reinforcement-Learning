
import java.util.ArrayList;

public class Simulator {

    public final char[][] track;

    private ICollisionHandler collisionHandler;
    private int numMoves;
    private State startState, currentState;
    private int[] policy;

    public Simulator(char[][] track, ICollisionHandler collisionHandler) {

        startState = getStartState(track);//this just gets the first start state possible, whatever.
        this.collisionHandler = collisionHandler;
        this.track = track;
        this.numMoves = 0;
        //init(); avoid some errors atm
    }

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

    private void readPolicy() {
        policy = FileHandler.readPolicy("qPolicy");
    }

    public State takeAction(State state, Action action) {

        System.out.println("Take action: Ax:" + action.x + " Ay: " + action.y);
        state.velocity.accelerate(action);
        currentState = traverse(state);
        return currentState;
    }

    //Moves the agent according to their current velocity and detects wall collisions using
    //a super cover implementaion of Bresenham's line algorithm
    private State traverse(State state) {

        ArrayList<Position> moves = new ArrayList();

        int x = state.position.x;
        int y = state.position.y;
        int vx = state.velocity.x;
        int vy = state.velocity.y;
        int x2 = x + vx;
        int y2 = y + vy;
        int count = 1 + Math.abs(vx) + Math.abs(vy);
//<<<<<<< HEAD
//
//        int xInc = 0;
//        int yInc = 0;
//        if (x2 > x) {
//            xInc = 1;
//        } else if (x2 < x) {
//            xInc = -1;
//        }
//        if (y2 > y) {
//            yInc = 1;
//        } else if (y2 < y) {
//            yInc = -1;
//        }
//
//        int error = vx - vy;
//=======
        int xInc = (x2 > x) ? 1 : (x2 == x) ? 0 : -1;
        int yInc = (y2 > y) ? 1 : (y2 == y) ? 0 : -1;
        int error = Math.abs(vx) - Math.abs(vy);

        int prevX = x;
        int prevY = y;
        vx *= 2;
        vy *= 2;//why??

        moves.add(new Position(x, y));

        for (; count > 0; --count) {

            System.out.println("Checking track at X:" + x + " ,Y:" + y + "   " + track[y][x]);

            if (track[y][x] == '#') {
                return collisionHandler.handleCollision(startState, new Position(prevX, prevY));
            } else if (track[y][x] == 'F') {
                state.finish = true;
                //endSimulation(); No we do not end the simulation, check the resulting position on the return...
                state.position = new Position(x, y);
                state.velocity = new Velocity(0, 0);//final states are always 0 velocity.
                return state;//need the final state for other code to know it's the final state...
            }

            if (error > 0) {
                prevX = x;
                x += xInc;
                error -= Math.abs(vy);
            } else if (error == 0) {
                prevX = x;
                prevY = y;
                x += xInc;
                y += yInc;
                count--;//affected both x and y so this was effectively 2 moves.
            } else {
                prevY = y;
                y += yInc;
                error += Math.abs(vx);
            }
            moves.add(new Position(x, y));
        }

        if (track[y2][x2] == '#') {
            return collisionHandler.handleCollision(startState, new Position(prevX, prevY));
        }
        state.position = new Position(x2, y2);
        return state;
    }

    //Terminate the simulation and print statistics
    private void endSimulation() {

        System.out.println("Agent reached the finish line!");
        System.out.println("Number of actions: " + numMoves);
        printTrack();
        //TODO add stats tracking and print out summary here

        System.exit(0);
    }

    //Print the racetrack with agent's location to the console
    public void printTrack() {

        int x = currentState.position.x;
        int y = currentState.position.y;

        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[0].length; j++) {
                if (x == i && y == j) {
                    System.out.print("A");
                } else {
                    System.out.print(track[j][i]);
                }
            }
            System.out.println("");
        }
    }
}
