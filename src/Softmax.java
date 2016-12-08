
public class Softmax {

    private static final int TEMPERATURE = 99999;     //tunable temperature parameter.  Higher values force greater chance of choosing randomly.  Lower values force selection to become greedy.

    public static Action getNextAction(State state, StateInfo info, StateIDMapper mapper, double[] qValues) {

        //assign probabilities to all potential actions
        double[] probabilityTable = new double[Action.VALID_ACTIONS.length];        //table containing probability that each of the potential accelerations should be chosen.
        double[] potentialStateQ = new double[Action.VALID_ACTIONS.length];         //table containing q values for the result of all possible actions

        for (int a = 0; a < Action.VALID_ACTIONS.length; a++) {
            Action action = Action.VALID_ACTIONS[a];
            State nextState = new State(state.position, state.velocity);
            nextState.velocity.changeVelocity(action);
            nextState.position.changePosition(nextState.velocity);
            potentialStateQ[a] = qValues[mapper.getStateIDFromState(state)];
        }

        //probability of choosing action a from domain
        for (int action = 0; action < Action.VALID_ACTIONS.length; action++) {
            double denominator = 0.0;
            for (int i = 0; i < Action.VALID_ACTIONS.length; i++) {
                denominator += (Math.exp(potentialStateQ[i]) / TEMPERATURE);
            }
            probabilityTable[action] = (Math.exp(potentialStateQ[action]) / TEMPERATURE) / denominator;
        }

        //choose a random action based on weighted probabilites
        double random = Math.random();
        double total = 0;

        for (int i = 0; i < Action.VALID_ACTIONS.length; i++) {
            total += probabilityTable[i];
            if (total >= random) {
                //choose action i
                return Action.VALID_ACTIONS[i];
            }
        }
        System.out.println("Unreacable line: softmax.");
        return new Action(-2, -2);
    }
}
