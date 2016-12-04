
public class Simulator {

    private Agent agent;
    private char[][] track;

    public Simulator() {
        
    }
    
    public Simulator(Agent agent, char[][] track) {
        this.agent = agent;
        this.track = track;
    }

    public State takeAction(int stateID, int action) {
        //TODO
        return new State();
    }

    public void run() {

        char location;
        do {
            int prevX = agent.state.x;
            int prevY = agent.state.y;
            //make agent go?

            location = track[agent.state.x][agent.state.y];
            if (location != '.') {
                printTrack();
            }

        } while (location != 'F');
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
