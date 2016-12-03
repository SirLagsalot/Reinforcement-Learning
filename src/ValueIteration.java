
public class ValueIteration extends PolicyMaker {
    
    
    public ValueIteration(StateIDMapper map, char[][] track, TrackSimulator sim) {
        super(map, track, sim);
    }

    public int[] createPolicy(){
        //TODO -- this is an int[] of size maximumPossibleStateID, so each position is the state and has a value of it's best action.
        return new int[0];
    }
}
