
import java.util.ArrayList;
import java.util.Random;

public class QLearner extends PolicyMaker {

    private double eta = 0.9;
    private double liklihoodToExplore = 1.5;

    private final double endEta = 0.8;
    private final double endLiklihoodToExplore = 0.05;
    
    private int states;
    int episodes = 0;
    private int iterations = 0;

    public QLearner(StateIDMapper map, char[][] track, Simulator sim) {
        super(map, track, sim);
        this.q = new double[idMap.getMaxState() + 1][9];
    }

    @Override
    public int[] createPolicy() {

        learnQ();
        int[] policy = new int[q.length];
        for (int i = 0; i < policy.length; i++) {
            policy[i] = maxA(i);
        }
        return policy;
    }

    private ArrayList<State> getStartingStates() {

        ArrayList<State> startLine = new ArrayList<>();
        for (int i = 0; i < simulator.track.length; i++) {
            for (int j = 0; j < simulator.track[i].length; j++) {
                if (simulator.track[i][j] == 'S') {
                    startLine.add(new State(new Position(j, i), new Velocity(0, 0)));
                }
            }
        }
        return startLine;
    }

    private void learnQ() {

        int currentStateID;
        ArrayList<State> startingStates = getStartingStates();
        int totalEpisodes = idMap.getMaxState();                                            //TODO: Justify or come up with better scale...
        double etaKneelingFactor = Math.pow(endEta / eta, 1 / (double) totalEpisodes);

        double exploreToGreedyKneelingFactor = Math.pow(endLiklihoodToExplore / liklihoodToExplore, 1 / (double) totalEpisodes);

        int episodesPerStartState = totalEpisodes / startingStates.size();
        episodes = episodesPerStartState;
        for (int startStateIndex = 0; startStateIndex < startingStates.size(); startStateIndex++) {
            
            System.out.println("BEGINNING AT START INDEX " + startStateIndex);

            for (int i = 0; i < episodesPerStartState; i++) {
                
                System.out.println("BEGINNING EPISODE " + i + " OF START " + startStateIndex);
                
                currentStateID = idMap.getStateIDFromState(startingStates.get(startStateIndex));
                System.out.println("Initial stateID:" + currentStateID);
                while (true) {
                    iterations++;
                    int action = Softmax.getNextAction(q[currentStateID]);
                    State currentState = this.idMap.GetStateFromID(currentStateID);
                    //System.out.println("Current state is: Px:" + currentState.position.x + " Py:" + currentState.position.y + " Vx:" + currentState.velocity.x + " Vy:" + currentState.velocity.y);
                    State result = this.simulator.takeAction(currentState, new Action(action));
                    //System.out.println("Resulting Position: X:" + result.position.x + " Y:" + result.position.y + "\n");
                    int reward = -1;
                    StateInfo resultInfo = this.idMap.getStateInfoFromPosition(result.position);
                    if (resultInfo.isFinal) {
                        reward = 0;
                    }
                    int newStateID = this.idMap.computeStateIDFromStateAndStateInfo(result, resultInfo);

                    int nextBestAction = maxA(newStateID);
                    q[currentStateID][action] = q[currentStateID][action] + (eta * (reward + (0.98 * q[newStateID][nextBestAction]) - q[currentStateID][action]));
                    currentStateID = newStateID;
                    System.out.println("\tNext stateID: " + currentStateID);
                    if (resultInfo.isFinal) {
                        System.out.println("Hit finish line, episode complete");
                        break;
                    }
                }
                this.eta *= etaKneelingFactor;
                this.liklihoodToExplore *= exploreToGreedyKneelingFactor;
            }
        }
        System.out.println("Finished Q-Learning");
    }

    private int maxA(int stateID) {

        Random rand = new Random();
        int bestIndex = 0;
        double bestResult = q[stateID][0];
        for (int i = 1; i < 9; i++) {
            if (q[stateID][i] > bestResult || (q[stateID][i] == bestResult && rand.nextInt(2) == 0)) {
                bestResult = q[stateID][i];
                bestIndex = i;
            }
        }
        return bestIndex;
    }
    
    @Override
    public int getIterations(){
        return iterations/episodes;
    }
}
