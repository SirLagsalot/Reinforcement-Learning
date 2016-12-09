
public class Main {

    public static void main(String[] args) {

        char[][] ITrack = TrackReader.readFile("./tracks/O-track.txt");
        StateIDMapper mapper = new StateIDMapper(ITrack);

        //char[][] LTrack = TrackReader.readFile("./tracks/L-track.txt");
        //char[][] OTrack = TrackReader.readFile("./tracks/O-track.txt");
        //char[][] RTrack = TrackReader.readFile("./tracks/R-track.txt");
        
        Simulator simulator = new Simulator(ITrack, new CollisionStop());
        
        PolicyMaker qLearner = new QLearner(mapper, ITrack, simulator);
        double[][] q = qLearner.createPolicy();
        FileHandler.exportPolicy(q, "policy");

        double[][] policy = FileHandler.importPolicy("policy");
        //System.out.println("Policy size: " + policy.length +  " " + policy[0].length);

        Tester tester = new Tester(simulator, mapper, policy);
    }

    private static void printTrack(char[][] track) {

        for (char[] row : track) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
