
public class Main {

    public static void main(String[] args) {

        boolean buildPolicy = true;        //Set me to true to create a new policy
        boolean algorithm = false;          //False for ValueIteration, true for Q-Learning
        int[] policy;
        int iterations = 0;
        int actions = 0;
        int collisions = 0;
        ICollisionHandler collisionHandler = new CollisionReset();

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
        System.out.println("******** VALUE ITERATION TRIALS ON R TRACK, COLLISION RESET ********");
        System.out.println("");

        for (int i = 0; i < 10; i++) {
            System.out.println("Trial " + (i + 1));
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

            iterations += policyMaker.getIterations();
            System.out.println("Number of iterations: " + policyMaker.getIterations());

            Tester tester = new Tester(new Simulator(RTrack, collisionHandler), mapper, policy);
            actions += tester.getActions();

            collisions += tester.getCollisions();
            System.out.println("Number of collisions: " + tester.getCollisions());

            System.out.println("");
        }
        System.out.println("---------------------------------------------");
        System.out.println("Total iterations: " + iterations);
        System.out.println("Average iterations: " + (iterations / 10));
        System.out.println("");
        System.out.println("Total actions: " + actions);
        System.out.println("Average actions: " + (actions / 10));
        System.out.println("");
        System.out.println("Total collisions: " + collisions);
        System.out.println("Average collisions: " + (collisions / 10));
    }
}
