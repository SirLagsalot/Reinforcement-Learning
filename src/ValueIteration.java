
import java.util.Random;

public class ValueIteration extends PolicyMaker {

    private double epsilon = 0.0001;        //stopping threshold
    private int numIterations;              //count of iterations required to build policy
    private double[][] qValues;             //Array holding the utility of all state action pairs

    public ValueIteration(StateIDMapper map, char[][] track, Simulator sim) {
        super(map, track, sim);
    }

    @Override
    public int[] createPolicy() {
        //TODO -- this is an int[] of size maximumPossibleStateID, so each position is the state and has a value of it's best action.
        return new int[0];
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

    private void setEpsilon(double epsilon) {
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
}
