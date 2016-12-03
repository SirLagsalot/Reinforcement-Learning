
import java.util.Random;

public class QLearner extends PolicyMaker {

    private double learningFactor;
    private double discountFactor;
    private Policy policy;
    private double[][] q;

    public QLearner(StateIDMapper map, char[][] track, TrackSimulator sim) {
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
    public int[] createPolicy() {
        //TODO
        initializeQ();
        learnQ();
        int[] policy = softMaxQ();
        return policy;
    }

    public void initializeQ() {
        q = new double[getMaxState(this.iDMap)][9];//array of every state and 9 actions.
    }

    public int getMaxState(StateIDMapper mapper) {
        StateInfo info = mapper.stateInfos.get(mapper.stateInfos.size() - 1);
        return info.stateID + (info.maxVelocityX - info.minVelocityX) * (info.maxVelocityY - info.minVelocityY) + (info.maxVelocityY - info.minVelocityY);
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
        double eta = 1;//this should vary with step size? //TODO
        double gamma = .1;//I guess?
        int currentStateID = 0;
        Random rand = new Random();
        //so each 'episode' is just like... a random round I guess? maybe have 100 episodes?
        for (int i = 0; i < 100; i++) {

            currentStateID = rand.nextInt(q.length);//TODO Bias this towards the end???
            while (true) {
                int action = maxA(currentStateID);//selectAction(currentStateID);
                State result = this.simulator.takeAction(currentStateID, action);
                int reward = -1;
                StateInfo resultInfo = this.iDMap.getStateInfoFromPosition(result.position);
                if (resultInfo.isFinal) {
                    reward = 0;
                }
                int newStateID = this.iDMap.computeStateIDFromStateAndStateInfo(result, resultInfo);
                int nextBestAction = maxA(newStateID);
                q[currentStateID][action] = q[currentStateID][action] + eta * (reward + gamma * (q[newStateID][nextBestAction] - q[currentStateID][action]));
                currentStateID = newStateID;
            }

        }
    }

    private int maxA(int stateID) {
        int bestIndex = 0;
        double bestResult = q[stateID][0];
        for (int i = 1; i < 9; i++) {
            if (q[stateID][i] > bestResult) {
                bestResult = q[stateID][i];
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    private int selectAction(int stateID) {
        //TODO
        return 1;
    }

    public int[] softMaxQ() {
        //todo
        return new int[0];
    }
}
