
import java.util.Random;

public class QLearner extends PolicyMaker {

    private double learningFactor;
    private double discountFactor;
    private double[][] q;
//    private double searchedBias = 1000;

    public QLearner(StateIDMapper map, char[][] track, Simulator sim) {
        super(map, track, sim);
    }

    //initialize all Q(s, a) arbitrarily
    //for all episodes
    //initialize s
    //repeat
    //choose a useing policy derived from Q
    //take action a, observe r and s'
    //update Q(s, a)
    //Q(s, a) = Q(s, a) + eta(r + gamma(max a' (Q(s', a') - Q(s, a)))
    //s = s'
    //until s is terminal state
    @Override
    public double[][] createPolicy() {
        initializeQ();
        learnQ();
        //int[] policy = softMaxQ();
        //return policy;
        return q;
    }

    public void initializeQ() {
        q = new double[idMap.getMaxState()][9];//array of every state and 9 actions.
//        for (int i = 0; i < idMap.getMaxState(); i++) {
//            State state = idMap.GetStateFromID(i);
//            if(track[state.position.y][state.position.x] == 'F'){
//                for (int j = 0; j < 9; j++) {
//                    q[i][j] += searchedBias;//All final positions are pre-treated as though they've been searched.
//                }
//            }
//        }
    }

    //for all episodes
    //initialize s -- maybe near finish?
    //repeat
    //choose a useing policy derived from Q
    //take action a, observe r and s'
    //update Q(s, a)
    //Q(s, a) = Q(s, a) + eta(r + gamma(max a' (Q(s', a') - Q(s, a)))
    //s = s'
    //until s is terminal state
    public void learnQ() {
//        for (int episodeStartID = 0; episodeStartID < q.length; episodeStartID++) {
//            for (int episodeStartAction = 0; episodeStartAction < q[0].length; episodeStartAction++) {
//                
//            }
//        }
        int totalEpisodes = 20 * idMap.getMaxState();//this.idMap.getMaxState()/4; //TODO: Justify or come up with better scale...
        double eta = 0.9;//startEta//this should vary with step size? //TODO
        double endEta = 0.1;
        double etaKneelingFactor = Math.pow(endEta / eta, 1 / (double) totalEpisodes);
        //maybe .9 because there is NO reward until the final state is reached...
        double gamma = 0.8;//I guess?
        double liklihoodToExplore = 1;//start liklihood to explore
        double endLiklihoodToExplore = 0.05;
        double exploreToGreedyKneelingFactor = Math.pow(endLiklihoodToExplore / liklihoodToExplore, 1 / (double) totalEpisodes);
        int currentStateID = 0;
        Random rand = new Random();
        for (int i = 0; i < totalEpisodes / idMap.getMaxState(); i++) {
            for (int j = 0; j < this.idMap.getMaxState(); j++) {
                //StateInfo info = idMap.stateInfos.get(j);
                //State state = new State(info.position, new Velocity(0,0));
                currentStateID = j;//idMap.computeStateIDFromStateAndStateInfo(state, info);

                //currentStateID = rand.nextInt(q.length);//TODO Bias this towards the end???
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
                        reward = 100;
                    }
                    int newStateID = this.idMap.computeStateIDFromStateAndStateInfo(result, resultInfo);

                    int nextBestAction = maxA(newStateID);
                    q[currentStateID][action] = q[currentStateID][action] + (eta * (reward + (gamma * q[newStateID][nextBestAction]) - q[currentStateID][action]));
                    currentStateID = newStateID;
//                if(isFirstUpdateOnState){
//                    q[currentStateID][action] += searchedBias;
//                }
                    if (resultInfo.isFinal) {
                        break;
                    }
                }
                //TODO: Kneel uhh... gamma or eta? at the end of everything. Something like gamma *= .95;
                eta *= etaKneelingFactor;
                liklihoodToExplore *= exploreToGreedyKneelingFactor;
                //lolemptycommit
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

//    public int[] softMaxQ() {
//        
//        int[] policy = new int[this.idMap.getMaxState()];
//        for (int i = 0; i < policy.length; i++) {
//            policy[i] = Softmax.getNextAction(q[i]);
//        }
//        return policy;
//    }
}
