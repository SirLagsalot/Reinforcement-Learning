
public class Main {

    public static void main(String[] args) {

        char[][] LTrack = Reader.readFile("./tracks/L-track.txt");
        char[][] OTrack = Reader.readFile("./tracks/O-track.txt");
        char[][] RTrack = Reader.readFile("./tracks/R-track.txt");

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
