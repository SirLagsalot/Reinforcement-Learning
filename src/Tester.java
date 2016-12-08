
import java.util.ArrayList;
import java.util.Random;

//Tester runs a simulation using a fully generated policy and records statistics
public class Tester {

    private final int[] policy;
    private final Simulator simulator;
    private final StateIDMapper mapper;

    public Tester(Simulator simulator, StateIDMapper mapper, int[] policy) {
        this.policy = policy;
        this.mapper = mapper;
        this.simulator = simulator;
        run();
    }

    private void run() {

        //Get list of starting positions
        ArrayList<Position> startLine = new ArrayList<>();
        for (int i = 0; i < simulator.track.length; i++) {
            for (int j = 0; j < simulator.track[i].length; j++) {
                if (simulator.track[i][j] == 'S') {
                    startLine.add(new Position(j, i));
                }
            }
        }
        //Randomly select a starting position
        Random random = new Random();
        State currentState = new State(startLine.remove(random.nextInt(startLine.size())), new Velocity(0, 0));

        while (true) {

            if (currentState.finish) {
                break;
            }
            simulator.printTrack();
            Action action = new Action(policy[mapper.getStateIDFromState(currentState)]);
            currentState = simulator.takeAction(currentState, action);
        }
    }
}
