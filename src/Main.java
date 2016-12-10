
public class Main {

    public static void main(String[] args) {

        boolean buildPolicy = true;        //Set me to true to create a new policy
        boolean algorithm = false;          //False for ValueIteration, true for Q-Learning
        int[] policy;
        ICollisionHandler collisionHandler = new CollisionStop();

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

        PolicyMaker policyMaker;
        if (algorithm) {
            policyMaker = new QLearner(mapper, RTrack, simulator);
        } else {
            policyMaker = new ValueIteration(mapper, RTrack, simulator);
        }

        if (buildPolicy) {
            policy = policyMaker.createPolicy();
            FileHandler.exportPolicy(policy, "policy");
        } else {
            policy = FileHandler.importPolicy("policy");
        }

        Tester tester = new Tester(simulator, mapper, policy);
    }
}
