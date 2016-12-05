
public class Simulator {

    private Agent agent;
    private ICollisionHandler collisionHandler;
    private char[][] track;
    private int numMoves;

    public Simulator() {

    }

    public Simulator(Agent agent, ICollisionHandler collisionHandler, char[][] track) {

        this.agent = agent;
        this.collisionHandler = collisionHandler;
        this.track = track;
        this.numMoves = 0;
    }

    public void run() {

        char location;
        do {
            Position prevPos = agent.state.position;

            location = track[agent.state.x][agent.state.y];

            if (location != '.') {
                printTrack();
            }

        } while (location != 'F');
    }

    public State takeAction(int stateID, int action) {
        //TODO
        return new State();
    }

    public void moveAgent() {

        this.numMoves++;

        //get action
        int[] action = new int[2];
        //TODO, link up getting an action

        accelerate(action[0], action[1]);

        traverse();

        printTrack();
    }

    //Updates the agent's current state by applying an acceleration
    private void accelerate(int Ax, int Ay) {

        assert (Ax >= -1 && Ax <= 1);
        assert (Ay >= -1 && Ay <= 1);

        //update velocities with 80% success rate
        if (Math.random() > 0.2) {
            this.agent.state = new State(agent.state.position, (agent.state.Vx + Ax) % 6, (agent.state.Vy + Ay) % 6);
        }
    }

    //Moves the agent according to their current velocity and detects wall collisions using
    //a super cover implementaion of Bresenham's line algorithm
    private void traverse() {

        int x = agent.state.x;
        int y = agent.state.y;
        int vx = agent.state.Vx;
        int vy = agent.state.Vy;
        int x2 = x + vx;
        int y2 = y + vy;
        int count = 1 + vx + vy;
        int xInc = (x2 > x) ? 1 : -1;
        int yInc = (y2 > y) ? 1 : -1;
        int error = vx - vy;

        vx *= 2;
        vy *= 2;

        for (; count > 0; --count) {

            if (track[y][x] == '#') {
                agent.state = collisionHandler.handleCollision(agent.state, new Position(x, y));
            } else if (track[y][x] == 'F') {
                endSimulation();
                return;
            }

            if (error > 0) {
                x += xInc;
                error -= vy;
            } else {
                y += yInc;
                error += vx;
            }
        }

        agent.state.position = new Position(x2, y2);
    }

    private void endSimulation() {

        System.out.println("Agent reached the finish line!");
        printTrack();
        //TODO add stats tracking and print out summary here

        System.exit(0);
    }

    private void printTrack() {

        int x = agent.state.x;
        int y = agent.state.y;

        for (int i = 0; i < track.length; i++) {
            for (int j = 0; j < track[0].length; j++) {
                if (x == i && y == i) {
                    System.out.print("A");
                } else {
                    System.out.print(track[i][j]);
                }
            }
            System.out.println("");
        }
    }
}
