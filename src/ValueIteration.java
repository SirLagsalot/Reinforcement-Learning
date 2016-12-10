
import java.util.Arrays;
import java.util.Random;

public class ValueIteration extends PolicyMaker {

    private final double epsilon = 0.00000000000000001;        //stopping threshold
    private final double gamma = 0.5;               //discount factor 
    private int numIterations;                      //count of iterations required to build policy
    private double eta = .9;

    public ValueIteration(StateIDMapper map, char[][] track, Simulator sim) {
        super(map, track, sim);
    }

    @Override
    public int[] createPolicy() {
        //TODO -- this is an int[] of size maximumPossibleStateID, so each position is the state and has a value of it's best action.
        init();

        double delta;
        double bellmanResidual = epsilon * (1 - gamma) / gamma;
        double[] vK = new double[this.idMap.getMaxState()+1];
        System.out.println("Bellman Residual: " + bellmanResidual);
        while (true) {
            boolean allvkPass = true;
            double[] oldVK = vK;
            double[] newVK = iterate(vK);
            vK = newVK;
            //System.out.println("Finished iteration: Delta = "+delta);
            for (int i = 0; i < vK.length; i++) {
                if (Math.abs(newVK[i] - oldVK[i]) >= epsilon) {
                    System.out.println("the too big vK difference was...: "+Math.abs(newVK[i] - oldVK[i]));
                    allvkPass = false;
                    
                    break;
                }
            }
            if (allvkPass) {
                break;
            }
        } //while (delta < bellmanResidual);

        //max v_k into policy: todo
        return getBestActions(vK);
    }

    private int[] getBestActions(double[] vK) {
        int[] policy = new int[vK.length];
        for (int stateID = 0; stateID < vK.length; stateID++) {
            double bestResult = -99999;
            int bestAction = 0;
            for (int i = 0; i < 9; i++) {
                double actionValue = 0;
                State state = idMap.GetStateFromID(stateID);
                State succActionState = simulator.takeActionDetermisistic(state, new Action(i));
                int succActionReward = -1;
                if (succActionState.finish == true) {
                    succActionReward = 0;
                }
                State failActionState = simulator.takeActionDetermisistic(state, new Action(4));//should be the 0,0 action...
                int failActionReward = -1;
                if (failActionState.finish == true) {
                    failActionReward = 0;
                }
                actionValue += .2 * (failActionReward + (eta * vK[idMap.getStateIDFromState(failActionState)]));
                actionValue += .8 * (succActionReward + (eta * vK[idMap.getStateIDFromState(succActionState)]));
                if (actionValue > bestResult) {
                    bestResult = actionValue;
                    bestAction = i;
                }
            }
            policy[stateID] = bestAction;
        }
        return policy;
    }

    //Arbitrarily assign utility values to qValues
    private void init() {

        this.q = new double[idMap.getMaxState()][9];       //TODO: need to get actual dimensions --easy(Wilson)
        Random random = new Random();

        for (double[] rows : q) {
            for (double stateUtility : rows) {
                stateUtility = random.nextDouble();
            }
        }
    }

    private double[] iterate(double[] vK) {
        double[] newVK = new double[vK.length];
        for (int i = 0; i < newVK.length; i++) {
            newVK[i] = getBestResult(vK, i);
        }
        return newVK;
    }

    private double getBestResult(double[] vK, int stateID) {
        double bestResult = -99999;
        for (int i = 0; i < 9; i++) {//for each action
            double actionValue = 0;
            State state = idMap.GetStateFromID(stateID);
            State succActionState = simulator.takeActionDetermisistic(state, new Action(i));
            int succActionReward = -1;
            if (succActionState.finish == true) {
                succActionReward = 0;
            }
            State failActionState = simulator.takeActionDetermisistic(state, new Action(4));//should be the 0,0 action...
            int failActionReward = -1;
            if (failActionState.finish == true) {
                failActionReward = 0;
            }
            actionValue += .2 * (failActionReward + (eta * vK[idMap.getStateIDFromState(failActionState)]));
            actionValue += .8 * (succActionReward + (eta * vK[idMap.getStateIDFromState(succActionState)]));
            if (actionValue > bestResult) {
                bestResult = actionValue;
            }
        }
        return bestResult;
    }

    private double[] iterateOLD() {

        double maxError = 0.0;
        double[][] newQValues = Arrays.copyOf(q, q.length);  //needs to be the old qValues, this double array thing is stupid
        //go through each state
        for (int s = 0; s < q.length; s++) {

            double currentUtility = this.idMap.GetStateFromID(s).getUtility();
            double bestAction = -9999;
            double[] nextStateUtility;

            //iterate over possible actions for current state
            for (int a = 0; a < Action.VALID_ACTIONS.length; a++) {
                //compute the state after taking action
                //compute the utility of said state
                newQValues[s][a] = getBellmanUtility(this.idMap.GetStateFromID(s));
                double currentError = Math.abs(newQValues[s][a] - q[s][a]);
                if (currentError > maxError) {
                    maxError = currentError;
                }
                //bellman equation
                //update qValues with that utility
            }
        }
//        return maxError;
        return new double[0];
    }

    private double getBellmanUtility(State currentState) {

        double result = 0.0;

        for (int action = 0; action < Action.VALID_ACTIONS.length; action++) {
            result += 0.8 * q[this.idMap.getStateIDFromState(currentState)][action];
        }

        return currentState.getUtility() + (gamma * result);
    }
}
