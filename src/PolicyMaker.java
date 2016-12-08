
public abstract class PolicyMaker {

    public StateIDMapper idMap;
    public Simulator simulator;
    public char[][] track;

    public PolicyMaker(StateIDMapper idMap, char[][] track, Simulator simulator) {
        
        this.idMap = idMap;
        this.track = track;
        this.simulator = simulator;
    }

    public double[][] createPolicy() {
        return new double[0][0];
    }
}
