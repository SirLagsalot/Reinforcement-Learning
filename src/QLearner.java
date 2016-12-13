
import java.util.ArrayList;
import java.util.Random;

public class QLearner extends PolicyMaker {

    private final double discountFactor = 0.98;
    private final double endEta = 0.8;

    private double eta = 0.9;
    private int numEpisodes;
    private int episodes = 0;
    private int iterations = 0;

    public QLearner(StateIDMapper map, char[][] track, Simulator sim, int numIterations) {
        super(map, track, sim);
        this.q = new double[idMap.getMaxState() + 1][9];
        this.numEpisodes = numIterations;
    }

    //Returns a trained policy for the racetrack problem
    @Override
    public int[] createPolicy() {

        learnQ();
        int[] policy = new int[q.length];
        for (int i = 0; i < policy.length; i++) {
            policy[i] = maxA(i);
        }
        return policy;
    }

    //Returns every state along the starting line.
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

    //Creates q[stateID][action] values which are 
    //refined over the input number of episodes
    //An equal number of episodes are trained from 
    //each valid starting state
    private void learnQ() {
        int currentStateID;
        ArrayList<State> startingStates = getStartingStates();
        int totalEpisodes = numEpisodes;
        double etaKneelingFactor = Math.pow(endEta / eta, 1 / (double) totalEpisodes);
        Softmax.setTemp(1);

        int episodesPerStartState = totalEpisodes / startingStates.size();
        episodes = episodesPerStartState;
        for (int startStateIndex = 0; startStateIndex < startingStates.size(); startStateIndex++) {

            System.out.println("BEGINNING AT START INDEX " + startStateIndex);

            for (int i = 0; i < episodesPerStartState; i++) {

                System.out.println("EXECUTING EPISODE " + i + " OF START " + startStateIndex);
                int episodeActions = 0;
                currentStateID = idMap.getStateIDFromState(startingStates.get(startStateIndex));
                while (true) {
                    episodeActions++;
                    iterations++;
                    int action = Softmax.getNextAction(q[currentStateID]);
                    State currentState = this.idMap.GetStateFromID(currentStateID);
                    State result = this.simulator.takeAction(currentState, new Action(action));
                    int reward = -1;
                    StateInfo resultInfo = this.idMap.getStateInfoFromPosition(result.position);
                    if (resultInfo.isFinal) {
                        reward = 0;
                    }
                    int newStateID = this.idMap.computeStateIDFromStateAndStateInfo(result, resultInfo);

                    int nextBestAction = maxA(newStateID);
                    q[currentStateID][action] = q[currentStateID][action] + (eta * (reward + (discountFactor * q[newStateID][nextBestAction]) - q[currentStateID][action]));
                    currentStateID = newStateID;
                    if (resultInfo.isFinal) {
                        System.out.println("Hit finish line after "+episodeActions+" actions, episode complete");
                        break;
                    }
                }
                this.eta *= etaKneelingFactor;
            }
        }
        System.out.println("Finished Q-Learning");
        System.out.println("Num episodes: "+totalEpisodes +"\nNum actions: "+iterations+"\n");
    }

    //Returns the currently-believed best action for the given state
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
    public int getIterations() {
        return iterations / episodes;
    }
}
