
public class ValueIteration extends PolicyMaker {

    public ValueIteration(StateIDMapper map, char[][] track, Simulator sim) {
        super(map, track, sim);
    }

    @Override
    public int[] createPolicy() {
        //TODO -- this is an int[] of size maximumPossibleStateID, so each position is the state and has a value of it's best action.
        return new int[0];
    }
}
