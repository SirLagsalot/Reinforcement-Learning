
public class PolicyMaker {

    StateIDMapper iDMap;
    char[][] track;
    TrackSimulator simulator;

    public PolicyMaker(StateIDMapper map, char[][] track, TrackSimulator sim) {
        iDMap = map;
        this.track = track;
        simulator = sim;
    }

    int[] createPolicy() {
        return new int[0];
    }
}
