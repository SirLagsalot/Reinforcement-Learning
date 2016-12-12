
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean buildPolicy = true;        //Set me to true to create a new policy
        boolean algorithm = false;          //False for ValueIteration, true for Q-Learning
        boolean useCollisionStop = true;
        
        int iterations = 0;
        int actions = 0;
        int collisions = 0;
        char[][] track = getTrackChoice();
        ICollisionHandler collisionHandler = getCollisionHandler();

        StateIDMapper mapper = new StateIDMapper(track);
        int[] policy = new int[mapper.getMaxState()+1];
        boolean validResponse = false;
        while (!validResponse) {
            validResponse = true;
            System.out.println("1.Create Policy\n2.Load policy\n3.Exit");
            int createOrLoad = scanner.nextInt();
            switch (createOrLoad) {
                case 1:
                    Simulator simulator = new Simulator(track, collisionHandler);
                    PolicyMaker policyMaker = getPolicyMaker(mapper, track, simulator);
                    policy = policyMaker.createPolicy();
                    break;
                case 2:
                    System.out.println("Enter file name for policy:");
                    String fileName = scanner.next();
                    policy = FileHandler.importPolicy(fileName);
                    break;
                case 3:
                    System.out.println("Exiting:");
                    System.exit(0);
                default:
                    validResponse = false;
                    System.out.println("Not valid option");
            }
        }

        System.out.println("Save Policy(y/n)?");
        String choice = scanner.next();
        switch (choice) {
            case "y":
                System.out.println("Enter filename");
                String fileName = scanner.next();
                FileHandler.exportPolicy(policy, fileName);
                System.out.println("Saved policy as: " + fileName);
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid selection: not saving");
        }

        System.out.println("Test Policy(y/n)?");
        choice = scanner.next();
        switch (choice) {
            case "y":
                Tester tester = new Tester(new Simulator(track, collisionHandler), mapper, policy);
                System.out.println("Number of collisions: " + tester.getCollisions());
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid selection: not testing");
        }

        //actions += tester.getActions();
        //collisions += tester.getCollisions();
//        System.out.println("");
//
//        System.out.println("---------------------------------------------");
//        System.out.println("Total iterations: " + iterations);
//        System.out.println("Average iterations: " + (iterations / 10));
//        System.out.println("");
//        System.out.println("Total actions: " + actions);
//        System.out.println("Average actions: " + (actions / 10));
//        System.out.println("");
//        System.out.println("Total collisions: " + collisions);
//        System.out.println("Average collisions: " + (collisions / 10));
    }

    public static ICollisionHandler getCollisionHandler() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1.Collision Stop\n2.Collision Reset\nChoose collision type(1 or 2): ");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    return new CollisionStop();
                case 2:
                    return new CollisionReset();
                default:
                    System.out.println("Invalid entry\n");
            }
        }
    }

    public static char[][] getTrackChoice() {
        //TrackReader.readFile("./tracks/L-track.txt");
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1.L-track\n2.O-track\n3.R-track\nChoose track: ");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    return TrackReader.readFile("./tracks/L-track.txt");
                case 2:
                    return TrackReader.readFile("./tracks/O-track.txt");
                case 3:
                    return TrackReader.readFile("./tracks/R-track.txt");
                default:
                    System.out.println("Invalid entry\n");
            }
        }
    }

    public static PolicyMaker getPolicyMaker(StateIDMapper map, char[][] track, Simulator simulator) {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("1.Q-Learning\n2.Value Iteration\nChoose policy learner(1 or 2): ");
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    return new QLearner(map, track, simulator);
                case 2:
                    return new ValueIteration(map, track, simulator);
                default:
                    System.out.println("Invalid entry\n");
            }
        }
    }
}
