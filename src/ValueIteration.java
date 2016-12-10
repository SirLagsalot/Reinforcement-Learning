
import java.util.Arrays;
import java.util.Random;

public class ValueIteration extends PolicyMaker {

    private final double epsilon = 0.000001;        //stopping threshold
    private final double gamma = 0.5;               //discount factor 
    private int numIterations;                      //count of iterations required to build policy

    public ValueIteration(StateIDMapper map, char[][] track, Simulator sim) {
        super(map, track, sim);
    }

    @Override
    public double[][] createPolicy() {
        //TODO -- this is an int[] of size maximumPossibleStateID, so each position is the state and has a value of it's best action.
        init();

        double delta;
        double bellmanResidual = epsilon * (1 - gamma) / gamma;
        System.out.println("Bellman Residual: "+bellmanResidual);
        do {
            delta = iterate();
            System.out.println("Finished iteration: Delta = "+delta);
        } while (delta < bellmanResidual);

        return new double[0][0];
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

    private double iterate() {

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
        return maxError;
    }

    private double getBellmanUtility(State currentState) {

        double result = 0.0;

        for (int action = 0; action < Action.VALID_ACTIONS.length; action++) {
            result += 0.8 * q[this.idMap.getStateIDFromState(currentState)][action];
        }

        return currentState.getUtility() + (gamma * result);
    }
}
