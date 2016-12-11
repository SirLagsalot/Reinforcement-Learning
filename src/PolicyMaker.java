
public abstract class PolicyMaker {

    public StateIDMapper idMap;
    public Simulator simulator;
    public char[][] track;
    public double[][] q;

    public PolicyMaker(StateIDMapper idMap, char[][] track, Simulator simulator) {

        this.idMap = idMap;
        this.track = track;
        this.simulator = simulator;
    }

    public int[] createPolicy() {
        return new int[0];
    }
    
    public int getIterations(){return 0;}
    
}
