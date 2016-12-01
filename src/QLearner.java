
public class QLearner implements ILearner {

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

    @Override
    public Policy learn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
