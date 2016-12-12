
import java.text.DecimalFormat;
import java.util.Random;

public class ValueIteration extends PolicyMaker {

    private final double epsilon = 0.00000000000000001;     //stopping threshold
    private final double gamma = 0.5;                       //discount factor 
    private final double eta = 0.9;

    private int numIterations;                              //count of iterations required to build policy

    public ValueIteration(StateIDMapper map, char[][] track, Simulator sim) {
        super(map, track, sim);
        init();
    }

    //Arbitrarily assign utility values to qValues
    private void init() {

        this.q = new double[idMap.getMaxState()][9];
        Random random = new Random();
        for (double[] rows : q) {
            for (double stateUtility : rows) {
                stateUtility = random.nextDouble();
            }
        }
    }

    @Override
    public int[] createPolicy() {

        double bellmanResidual = epsilon * (1 - gamma) / gamma;
        double[] vK = new double[this.idMap.getMaxState() + 1];
        DecimalFormat df = new DecimalFormat("0.0000");

        while (true) {

            numIterations++;
            boolean allvkPass = true;
            double[] oldVK = vK;
            System.out.println("Computing new Vk[] for all states\n");
            double[] newVK = iterate(vK);
            vK = newVK;

            for (int i = 0; i < vK.length; i++) {
                if (Math.abs(newVK[i] - oldVK[i]) >= bellmanResidual) {
                    System.out.println("Iteration " + numIterations + ": Delta(Vk) for stateID " + i + ": " + df.format(Math.abs(newVK[i] - oldVK[i])) + " is > residual (" + bellmanResidual + ")");
                    allvkPass = false;
                    break;
                }
            }
            if (allvkPass) {
                System.out.println("ALL Vk < BELLMAN RESIDUAL");
                break;
            }
        }
        System.out.println("\nNumber of iterations: " + numIterations+"\n");
        return getBestActions(vK);
    }

    //Update expected utility of all states
    private double[] iterate(double[] vK) {

        double[] newVK = new double[vK.length];
        for (int i = 0; i < newVK.length; i++) {
            newVK[i] = getBestResult(vK, i);
        }
        return newVK;
    }

    //Returns the best utility value currently possible for a given state
    private double getBestResult(double[] vK, int stateID) {

        double bestResult = Double.NEGATIVE_INFINITY;

        //For each action, calculate greatest expected utility
        for (int i = 0; i < 9; i++) {

            double actionValue = 0;
            State state = idMap.GetStateFromID(stateID);
            State succActionState = simulator.takeActionDetermisistic(state, new Action(i));
            int succActionReward = -1;
            if (succActionState.finish == true) {
                succActionReward = 0;
            }
            State failActionState = simulator.takeActionDetermisistic(state, new Action(4));    //Don't accelerate
            int failActionReward = -1;
            if (failActionState.finish == true) {
                failActionReward = 0;
            }
            actionValue += 0.2 * (failActionReward + (eta * vK[idMap.getStateIDFromState(failActionState)]));
            actionValue += 0.8 * (succActionReward + (eta * vK[idMap.getStateIDFromState(succActionState)]));
            
            //if it's better than the current best, update the current best
            if (actionValue > bestResult) {
                bestResult = actionValue;
            }
        }
        return bestResult;
    }

    //returns the ACTIONS which provide the most utility for each state
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
                State failActionState = simulator.takeActionDetermisistic(state, new Action(4));    //Don't accelerate
                int failActionReward = -1;
                if (failActionState.finish == true) {
                    failActionReward = 0;
                }
                actionValue += 0.2 * (failActionReward + (eta * vK[idMap.getStateIDFromState(failActionState)]));
                actionValue += 0.8 * (succActionReward + (eta * vK[idMap.getStateIDFromState(succActionState)]));
                if (actionValue > bestResult) {
                    bestResult = actionValue;
                    bestAction = i;
                }
            }
            policy[stateID] = bestAction;
        }
        System.out.println("Done building Value Iteration Policy after " + numIterations + " iterations!\n\n");
        return policy;
    }
    
    @Override
    public int getIterations(){
        return numIterations;
    }
}
