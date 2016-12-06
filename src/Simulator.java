
import java.util.ArrayList;
import java.util.Random;

public class Simulator {

    private ICollisionHandler collisionHandler;
    private char[][] track;
    private int numMoves;
    private State startState, currentState;


    public Simulator(char[][] track, ICollisionHandler collisionHandler) {
        startState = getStartState(track);
        this.collisionHandler = collisionHandler;
        this.track = track;
        this.numMoves = 0;
        init();
    }
    
    private State getStartState(char[][] track){
         for (int row = 0; row < track.length; row++) {
             for (int col = 0; col < track[row].length; col++) {
                 if(track[row][col] == 'S')
                     return new State(col, row, 0, 0);
             }
        }
        
        return new State();
    }

    private void init() {

        //Find all spaces on the starting line
        ArrayList<Position> startLine = new ArrayList<>();
        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[i].length; j++) {
                if (track[i][j] == 'S') {
                    startLine.add(new Position(i, j));
                }
            }
        }

        //Randomly select one as the starting position
        Random random = new Random();
        startState = new State(startLine.get(random.nextInt(startLine.size())), new Velocity(0, 0));        //Instantiating new state
    }

    public void run() {

        //int[] policy = agent.learner.createPolicy();
        while (true) {
            //take action according to policy
            //int stateID = agent.stateInfo.stateID;
            //go(stateID, new Action(policy[stateID]));
        }
    }

    public State takeAction(State state, Action action) {

        int[] acceleration = action.getAction();
        System.out.println("Take action: Ax:"+acceleration[0] + " Ay: "+acceleration[1]);
        State acceleratedState = accelerate(state, acceleration[0], acceleration[1]);
        return traverse(acceleratedState);
    }

    //Updates the agent's current state by applying an acceleration
    private State accelerate(State state, int Ax, int Ay) {

        assert (Ax >= -1 && Ax <= 1) : "Invalid X acceleration.";
        assert (Ay >= -1 && Ay <= 1) : "Invalid Y acceleration.";

        //update velocities with 80% success rate
        if (Math.random() > 0.2) {
            int Vx = (state.velocity.x + Ax);
            if (Vx > 5) {
                Vx = 5;
            }
            if (Vx < -5) {
                Vx = -5;
            }
            int Vy = (state.velocity.y + Ay);
            if (Vy > 5) {
                Vy = 5;
            }
            if (Vy < -5) {
                Vy = -5;
            }
            Velocity v = new Velocity(Vx, Vy);
            return new State(state.position, v);
        }
        return state;
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
        int count = 1 + vx + vy;
        int xInc = (x2 > x) ? 1 : -1;
        int yInc = (y2 > y) ? 1 : -1;
        int error = vx - vy;

        vx *= 2;
        vy *= 2;

        moves.add(new Position(x, y));

        for (; count > 0; --count) {

            if (track[y][x] == '#') {
                return collisionHandler.handleCollision(startState, moves.get(moves.size() - 1));
            } else if (track[y][x] == 'F') {
                endSimulation();
                return state;
            }

            if (error > 0) {
                x += xInc;
                error -= vy;
            } else {
                y += yInc;
                error += vx;
            }

            moves.add(new Position(x, y));
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
    private void printTrack() {

        int x = currentState.position.x;
        int y = currentState.position.y;

        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[0].length; j++) {
                if (x == i && y == j) {
                    System.out.print("A");
                } else {
                    System.out.print(track[i][j]);
                }
            }
            System.out.println("");
        }
    }
}
