
import java.util.Random;

public class QLearner extends PolicyMaker {

    private double learningFactor;
    private double discountFactor;
    private double[][] q;
//  private double searchedBias = 1000;

    public QLearner(StateIDMapper map, char[][] track, Simulator sim) {
        super(map, track, sim);
    }

    @Override
    public double[][] createPolicy() {

        this.q = new double[idMap.getMaxState()][9];
        learnQ();
        return q;
    }

    private void learnQ() {

        int totalEpisodes = 50 * idMap.getMaxState();       //TODO: Justify or come up with better scale...
        double eta = 0.9;
        double endEta = 0.1;
        double etaKneelingFactor = Math.pow(endEta / eta, 1 / (double) totalEpisodes);
        double gamma = 0.1;                                 //I guess?
        double liklihoodToExplore = 1.5;                    //start liklihood to explore
        double endLiklihoodToExplore = 0.05;
        double exploreToGreedyKneelingFactor = Math.pow(endLiklihoodToExplore / liklihoodToExplore, 1 / (double) totalEpisodes);
        int currentStateID;

        for (int i = 0; i < totalEpisodes / idMap.getMaxState(); i++) {
            for (int j = 0; j < this.idMap.getMaxState(); j++) {
//              StateInfo info = idMap.stateInfos.get(j);
//              State state = new State(info.position, new Velocity(0,0));
                currentStateID = j;
//              idMap.computeStateIDFromStateAndStateInfo(state, info);
//              currentStateID = rand.nextInt(q.length);//TODO Bias this towards the end???
                System.out.println("Initial stateID:" + currentStateID);
                while (true) {
//                boolean isFirstUpdateOnState = false;
                    //TODO: Choose action randomly, with SLIGHT weight towards best action, kneel it too I guess...
                    int action = Softmax.getNextAction(q[currentStateID]);
//                if(q[currentStateID][action] == 0){//if it hasn't been tested before, mark it tested by giving it preference.
//                    isFirstUpdateOnState = true;
//                }
                    State currentState = this.idMap.GetStateFromID(currentStateID);
                    System.out.println("Current state is: Px:" + currentState.position.x + " Py:" + currentState.position.y + " Vx:" + currentState.velocity.x + " Vy:" + currentState.velocity.y);
                    //System.out.println("Taking action: "+action);
                    State result = this.simulator.takeAction(currentState, new Action(action));
                    System.out.println("Resulting Position: X:" + result.position.x + " Y:" + result.position.y + "\n");
                    int reward = -1;
                    StateInfo resultInfo = this.idMap.getStateInfoFromPosition(result.position);
                    if (resultInfo.isFinal) {
                        reward = 0;
                    }
                    int newStateID = this.idMap.computeStateIDFromStateAndStateInfo(result, resultInfo);

                    int nextBestAction = maxA(newStateID);
                    //q[currentStateID][action] = q[currentStateID][action] + (eta * (reward + (gamma * q[newStateID][nextBestAction]) - q[currentStateID][action]));
                    q[currentStateID][action] = q[currentStateID][action] + (0.9 * (reward + (0.9 * q[newStateID][nextBestAction]) - q[currentStateID][action]));
                    currentStateID = newStateID;
//                if(isFirstUpdateOnState){
//                    q[currentStateID][action] += searchedBias;
//                }
                    if (resultInfo.isFinal) {
                        break;
                    }
                }

                eta *= etaKneelingFactor;             //TODO: Kneel uhh... gamma or eta? at the end of everything. Something like gamma *= .95;
                liklihoodToExplore *= exploreToGreedyKneelingFactor;
            }
        }
        System.out.println("finished q learning");
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
}
