
public class QLearner extends PolicyMaker {

    private double learningFactor;
    private double discountFactor;
    private Policy policy;

    public QLearner() {

    }

    //initialize all Q(s, a) arbitrarily
    //for all episodes
    //initialize s
    //repeat
    //choose a useing policy derived from Q
    //take action a, observe r and s'
    //update Q(s, a)
    //Q(s, a) = Q(s, a) + eta(r + gamma(max a' (Q(s', a') - Q(s, a)))
    //s = s'
    //until s is terminal state

    public int[] createPolicy(){
        //TODO
        return new int[0];
    }
}
