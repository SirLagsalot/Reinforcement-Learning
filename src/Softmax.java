
//Softmax function returns an action from the set of possible actions according to a weighted probability distribution
public class Softmax {

    private static double temperature = 1.0;     //tunable temperature parameter.  Higher values force greater chance of choosing randomly.  Lower values force selection to become greedy.

    public static int getNextAction(double[] qValues) {

        //assign probabilities to all potential actions
        double[] probabilityTable = new double[qValues.length];        //table containing probability that each of the potential accelerations should be chosen.

        double sum = 0.0;
        for (double q : qValues) {
            sum += Math.exp(q / temperature);
        }

        for (int i = 0; i < qValues.length; i++) {
            double top = Math.exp(qValues[i] / temperature);
            probabilityTable[i] = top / sum;
        }

        //choose a random action based on weighted probabilites
        double random = Math.random();
        double total = 0.0;

        for (int i = 0; i < qValues.length; i++) {
            total += probabilityTable[i];
            if (total >= random) {
                //choose action i
                return i;
            }
        }
        System.out.println("Unreacable line: softmax.");
        System.exit(-1);
        return -2;
    }

    public static void setTemp(double temp) {
        temperature = temp;
    }
}
