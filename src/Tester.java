
import java.util.ArrayList;
import java.util.Random;

//Tester runs a simulation using a fully generated policy and records statistics
public class Tester {

    public static boolean wait = true;

    private final double[][] policy;
    private final Simulator simulator;
    private final StateIDMapper mapper;
//    private final GUI gui;

    public Tester(Simulator simulator, StateIDMapper mapper, double[][] policy) {
        this.policy = policy;
        this.mapper = mapper;
        this.simulator = simulator;
//        this.gui = new GUI(simulator.track);
        run();
    }

    private void run() {

        //Get list of starting positions
        ArrayList<Position> startLine = new ArrayList<>();
        for (int i = 0; i < simulator.track.length; i++) {
            for (int j = 0; j < simulator.track[i].length; j++) {
                if (simulator.track[i][j] == 'S') {
                    startLine.add(new Position(j, i));
                }
            }
        }
        //Randomly select a starting position
        Random random = new Random();
        State currentState = new State(startLine.remove(random.nextInt(startLine.size())), new Velocity(0, 0));

        int actionCount = 0;
        Softmax.setTemp(0.1);
        while (true) {
            //gui.renderTrack(currentState.position, false);
            //while (wait) {      //this is kind of a sloppy way of making the program wait.. 
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException ex) {
//                    System.out.println(ex);
//                }
            //}
            wait = true;
            if (currentState.finish) {
                break;
            }
            Action action = new Action(Softmax.getNextAction(policy[mapper.getStateIDFromState(currentState)]));
            currentState = simulator.takeAction(currentState, action);
            simulator.printTrack();
            actionCount++;
        }
        System.out.println("Agent took " + actionCount + " actions to find the finish line.");
    }
}
