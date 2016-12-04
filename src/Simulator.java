
public class Simulator {

    private Agent agent;
    private ICollisionHandler collisionHandler;
    private char[][] track;

    public Simulator() {

    }

    public Simulator(Agent agent, ICollisionHandler collisionHandler, char[][] track) {

        this.agent = agent;
        this.collisionHandler = collisionHandler;
        this.track = track;
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

        //get action
        int[] action = new int[2];
        //TODO, link up getting an action

        accelerate(action[0], action[1]);

        traverse();

        printTrack();
    }

    private void accelerate(int Ax, int Ay) {

        assert (Ax >= -1 && Ax <= 1);
        assert (Ay >= -1 && Ay <= 1);

        //update velocities with 80% success rate
        if (Math.random() > 0.2) {
            State state = new State();
            state.Vx = (agent.state.Vx + Ax) % 6;
            state.Vy = (agent.state.Vy + Ay) % 6;
            state.position = agent.state.position;
            agent.state = state;
        }
    }

    /**
     * Bresenham-based super cover algorithm.
     *
     * Checks the board from the top left corner using y for rows and x for
     * columns. If a # character is found as one of the intersecting spaces it
     * is returned to indicate a collision. If all spaces are either . , s, or
     * f, the intended destination position is returned based on initial
     * position and the velocity vector. Up to the simulator to check intended
     * destination vs actual destination returned by this method.
     *
     * @param x position x
     * @param y position y
     * @param vx velocity x
     * @param vy velocity y
     * @return position of either the intended destination or the first
     * collision
     */
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
