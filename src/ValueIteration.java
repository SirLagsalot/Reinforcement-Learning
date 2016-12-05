
import java.util.Arrays;
import java.util.Random;

public class ValueIteration extends PolicyMaker {

    private final double epsilon = 0.000001;        //stopping threshold
    private final double gamma = 0.5;               //discount factor 
    private int numIterations;                      //count of iterations required to build policy
    private double[][] stateUtilities;              //Array holding the utility of all state action pairs
    private StateIDMapper mapper = new StateIDMapper(track);

    public ValueIteration(StateIDMapper map, char[][] track, Simulator sim) {
        super(map, track, sim);
    }

    @Override
    public int[] createPolicy() {
        //TODO -- this is an int[] of size maximumPossibleStateID, so each position is the state and has a value of it's best action.
        init();

        double delta;
        double bellmanResidual = epsilon * (1 - gamma) / gamma;
        do {
            delta = iterate();
        } while (delta < bellmanResidual);

        return new int[]{0, 0};
    }

    //Arbitrarily assign utility values to qValues
    private void init() {

        this.stateUtilities = new double[100][9];       //TODO: need to get actual dimensions
        Random random = new Random();

        for (double[] rows : stateUtilities) {
            for (double stateUtility : rows) {
                stateUtility = random.nextDouble();
            }
        }
    }

    private double iterate() {

        double maxError = 0.0;
        double[][] newQValues = Arrays.copyOf(stateUtilities, stateUtilities.length);  //needs to be the old qValues, this double array thing is stupid
        //go through each state
        for (int s = 0; s < stateUtilities.length; s++) {

            double currentUtility = mapper.GetStateFromID(s).getUtility();
            double bestAction = -9999;
            double[] nextStateUtility;

            //iterate over possible actions for current state
            for (int a = 0; a < Action.VALID_ACTIONS.length; a++) {
                //compute the state after taking action
                //compute the utility of said state
                newQValues[s][a] = getBellmanUtility(mapper.GetStateFromID(s));
                double currentError = Math.abs(newQValues[s][a] - stateUtilities[s][a]);
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

        for (Action action : Action.VALID_ACTIONS) {
            result += 0.8 * stateUtilities[mapper.getStateIDFromState(currentState)][action.toInt()];
        }

        return currentState.getUtility() + (gamma * result);
    }
}
