
import java.util.Random;

public class ValueIteration extends PolicyMaker {

    private final double epsilon = 0.0001;      //stopping threshold
    private final double gamma = 0.1;           //discount factor 
    private int numIterations;                  //count of iterations required to build policy
    private double[][] qValues;                 //Array holding the utility of all state action pairs
    private State[] states = new State[100];    //TODO: link up states
    private StateIDMapper mapper = new StateIDMapper(track);

    public ValueIteration(StateIDMapper map, char[][] track, Simulator sim) {
        super(map, track, sim);
    }

    @Override
    public int[] createPolicy() {
        //TODO -- this is an int[] of size maximumPossibleStateID, so each position is the state and has a value of it's best action.
        init();

        return new int[]{0, 0};
    }

    //Arbitrarily assign utility values to qValues
    private void init() {

        this.qValues = new double[100][9];       //TODO: need to get actual dimensions
        Random random = new Random();

        for (double[] rows : qValues) {
            for (double stateUtil : rows) {
                stateUtil = random.nextDouble();
            }
        }
    }

    private void iterate() {

        double maxError = 0.0;
        double[] newQValues = new double[100];  //needs to be the old qValues, this double array thing is stupid
        //go through each state
        for (int s = 0; s < states.length; s++) {

            double currentUtility = states[s].getReward();
            double bestAction = -9999;
            double[] nextStateUtility;

            newQValues[s] = states[s].getReward() + bellman(states[s]);

            //iterate over possible actions for current state
            for (Action action : Action.VALID_ACTIONS) {
                //compute the state after taking action
                //compute the utility of said state
                //bellman equation
                //update qValues with that utility
            }
        }
    }

    private double bellman(State currentState) {

        double result = 0.0;

        for (Action action : Action.VALID_ACTIONS) {
            result += 0.8 * qValues[mapper.getStateIDFromState(currentState)][action.toInt()];
        }

        return result;
    }
}
