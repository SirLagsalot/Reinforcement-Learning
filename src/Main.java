
public class Main {

    public static void main(String[] args) {

        char[][] LTrack = TrackReader.readFile("./tracks/L-track.txt");
        StateIDMapper mapper = new StateIDMapper(LTrack);

        //char[][] OTrack = TrackReader.readFile("./tracks/O-track.txt");
        //char[][] RTrack = TrackReader.readFile("./tracks/R-track.txt");
        printTrack(LTrack);
        //PolicyMaker qLearner = new QLearner(mapper, LTrack, new Simulator(LTrack, new CollisionStop()));
        //qLearner.createPolicy();

        PolicyMaker valueIteration = new ValueIteration(mapper, LTrack, new Simulator(LTrack, new CollisionStop()));
        valueIteration.createPolicy();

        printTrack(LTrack);
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
