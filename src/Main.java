
public class Main {

    public static void main(String[] args) {

        char[][] LTrack = TrackReader.readFile("./tracks/L-track.txt");
        StateIDMapper lTrackStateMapper = new StateIDMapper(LTrack);
        char[][] OTrack = TrackReader.readFile("./tracks/O-track.txt");
        char[][] RTrack = TrackReader.readFile("./tracks/R-track.txt");
        PolicyMaker qLearner = new QLearner(lTrackStateMapper, LTrack, new TrackSimulator());
        
        printTrack(LTrack);
        printTrack(OTrack);
        printTrack(RTrack);
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
