
public class Main {

    public static void main(String[] args) {
        
        boolean buildPolicy = false;         //Set me to true to create a new policy
        double[][] policy;
        ICollisionHandler collisionHandler = new CollisionStop();

//        char[][] ITrack = TrackReader.readFile("./tracks/I-track.txt");
//        StateIDMapper mapper = new StateIDMapper(ITrack);
//        Simulator simulator = new Simulator(ITrack, collisionHandler);
//        PolicyMaker qLearner = new QLearner(mapper, ITrack, simulator);

//        char[][] LTrack = TrackReader.readFile("./tracks/L-track.txt");
//        StateIDMapper mapper = new StateIDMapper(LTrack);
//        Simulator simulator = new Simulator(LTrack, collisionHandler);
//        PolicyMaker qLearner = new QLearner(mapper, LTrack, simulator);

//        char[][] OTrack = TrackReader.readFile("./tracks/O-track.txt");
//        StateIDMapper mapper = new StateIDMapper(OTrack);
//        Simulator simulator = new Simulator(OTrack, collisionHandler);
//        PolicyMaker qLearner = new QLearner(mapper, OTrack, simulator);

        char[][] RTrack = TrackReader.readFile("./tracks/R-track.txt");
        StateIDMapper mapper = new StateIDMapper(RTrack);
        Simulator simulator = new Simulator(RTrack, collisionHandler);
        PolicyMaker qLearner = new QLearner(mapper, RTrack, simulator);

        if (buildPolicy) {
            policy = qLearner.createPolicy();
            FileHandler.exportPolicy(policy, "policy");
        } else {
            policy = FileHandler.importPolicy("policy");
        }

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
