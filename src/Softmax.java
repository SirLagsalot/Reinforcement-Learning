
public class Softmax {

    private static final int TEMPERATURE = 99999;     //tunable temperature parameter.  Higher values force greater chance of choosing randomly.  Lower values force selection to become greedy.

    public static int[] getNextAction(State state, StateInfo info, double[] qValues) {

        //assign probabilities to all potential actions
        int domain = (info.maxVelocityX - info.minVelocityX) * (info.maxVelocityY - info.minVelocityY);

        double[] probabilityTable = new double[domain];         //table containing probability that each of the potential accelerations should be chosen.

        //probability of choosing action a from domain
        for (int a = 0; a < domain; a++) {
            double denominator = 0.0;
            for (int i = 0; i < domain; i++) {
                denominator += (Math.exp(qValues[info.stateID]) / TEMPERATURE);     //this isnt quite right, need to sum Math.exp(value of Q(a) for all possible a values, dividing each by temp
            }
            probabilityTable[a] = (Math.exp(qValues[info.stateID]) / TEMPERATURE) / denominator;
        }

        //choose a random action based on weighted probabilites
        double random = Math.random();
        double total = 0;

        for (int i = 0; i < Action.VALID_ACTIONS.length; i++) {

            total += probabilityTable[i];
            if (total >= random) {
                //choose action i
                return Action.VALID_ACTIONS[i].getAction();
            }
        }
        System.out.println("Unreacable line: softmax.");
        return new int[]{-2, -2};
    }
}
