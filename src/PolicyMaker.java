
public class PolicyMaker {

    public StateIDMapper iDMap;
    public Simulator simulator;
    public char[][] track;

    public PolicyMaker(StateIDMapper map, char[][] track, Simulator sim) {
        iDMap = map;
        this.track = track;
        simulator = sim;
    }

    public int[] createPolicy() {
        return new int[0];
    }
}
