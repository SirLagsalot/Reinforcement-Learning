
public class Softmax extends Policy {

    private static int temperature = 99999;     //tunable temperature parameter.  Higher values force greater chance of choosing randomly.  Lower values force selection to become greedy.

    public Softmax() {

    }

    @Override
    public int[] getNextAction(State state, StateInfo info, double[] qValues) {

        //assign probabilities to all potential actions
        int domain = (info.maxVelocityX - info.minVelocityX) * (info.maxVelocityY - info.minVelocityY);
        double[] probabilityTable = new double[domain];     //table containing probability that each of the potential accelerations should be chosen.

        //probability of choosing action a from domain
        for (int a = 0; a < domain; a++) {
            double denominator = 0.0;

            for (int i = 0; i < domain; i++) {
                denominator += (Math.exp(qValues[info.stateID]) / temperature);     //this isnt quite right, need to sum Math.exp(value of Q(a) for all possible a values, dividing each by temp
            }
            probabilityTable[a] = (Math.exp(qValues[info.stateID]) / temperature) / denominator;
        }

        //choose a random action based on weighted probabilites
        double random = Math.random();
        double total = 0;

        for (int i = 0; i < 9; i++) {
            total += probabilityTable[0];
            if (total >= random) {

                //choose action i
                switch (i) {
                    case 0:
                        return new int[]{-1, -1};
                    case 1:
                        return new int[]{-1, 0};
                    case 2:
                        return new int[]{-1, 1};
                    case 3:
                        return new int[]{0, -1};
                    case 4:
                        return new int[]{0, 0};
                    case 5:
                        return new int[]{0, 1};
                    case 6:
                        return new int[]{1, -1};
                    case 7:
                        return new int[]{1, 0};
                    case 8:
                        return new int[]{1, 1};
                    default:
                        System.out.println("Unreacable line: softmax.");
                        System.exit(i);
                }
            }
        }
        return new int[]{-2, -2};
    }
}
