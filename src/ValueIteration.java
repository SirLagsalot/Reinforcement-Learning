
import java.util.Random;

public class ValueIteration extends PolicyMaker {

    private Double epsilon = 0.0001;            //stopping threshold
    private int numIterations;                  //count of iterations required to build policy
    private Double[][] qValues;                 //Array holding the utility of all state action pairs
    private State[] states = new State[100];    //TODO: link up states 

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

        this.qValues = new Double[100][9];       //TODO: need to get actual dimensions
        Random random = new Random();

        for (Double[] rows : qValues) {
            for (Double stateUtil : rows) {
                stateUtil = random.nextDouble();
            }
        }
    }

    private void setEpsilon(Double epsilon) {
        this.epsilon = epsilon;
    }

    private void solve() {

        double gamma = 0.0;
        double threshold;

        if (gamma == 1) {
            threshold = epsilon;
        } else {
            threshold = epsilon * (1.0 - gamma) / gamma;
        }

        init();

        boolean done = false;
        this.numIterations = 0;

        while (!done) {

            double maxError = -1.0;

            for (;;) {

            }
        }
    }

    private void iterate() {

        //go through each state
        for (State state : states) {

            Double reward = state.getReward();
            Double bestAction = Double.NEGATIVE_INFINITY;

            //iterate over possible actions for current state
            StateIDMapper mapper = new StateIDMapper(track);
            int stateID = mapper.getStateIDFromState(state);
            StateInfo info = mapper.getStateInfoFromPosition(state.position);

            //get set of possible actions
            //Set actions = Action.validActions;
        }
    }
}
