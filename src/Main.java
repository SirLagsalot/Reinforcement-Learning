
public class Main {

    public static void main(String[] args) {

        char[][] ITrack = TrackReader.readFile("./tracks/I-track.txt");
        StateIDMapper mapper = new StateIDMapper(ITrack);

        //char[][] OTrack = TrackReader.readFile("./tracks/O-track.txt");
        //char[][] RTrack = TrackReader.readFile("./tracks/R-track.txt");
        printTrack(ITrack);
        Simulator simulator = new Simulator(ITrack, new CollisionStop());
        PolicyMaker qLearner = new QLearner(mapper, ITrack, simulator);
       double[][] q = qLearner.createPolicy();
        //FileHandler.exportPolicy(policy, "policy");
        //int[] p = FileHandler.importPolicy("test");

        Tester tester = new Tester(simulator, mapper, q);
        
        //PolicyMaker valueIteration = new ValueIteration(mapper, LTrack, new Simulator(LTrack, new CollisionStop()));
        //valueIteration.createPolicy();

        //printTrack(LTrack);
        //printTrack(OTrack);
        //printTrack(RTrack);

        //char[][] test = {{'.', '#', '.'}, {'.', '.', '.'}, {'.', '.', '.'}};
        //printTrack(test);
        //Position p = Bresenham.checkCollision(1, 1, 1, 1, test);
        //System.out.println(p.x);
        //System.out.println(p.y);
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
