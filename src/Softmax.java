
public class Softmax extends Policy {

    private double[] probabilityTable = new double[9];     //table containing probability that each of the 11 potential accelerations should be chosen.

    public Softmax() {

    }

    @Override
    public int[] getNextAction(State state, StateInfo info) {

        //assign probabilities to all potential actions
        double temperature = 99999;
        //get Q(s') for all possible actions
        
        //
        //
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
        return new int[] {-2, -2};
    }
}
