
public class Simulator {

    private Agent agent;
    private char[][] track;

    public Simulator(Agent agent, char[][] track) {
        this.agent = agent;
        this.track = track;
    }

    public void run() {

        boolean done = false;

        do {
            //make agent go?

            printTrack();

        } while (!done);
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
