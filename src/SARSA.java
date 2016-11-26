
public class SARSA implements Learner {

    public SARSA() {

    }

    //initialize all Q(s, a) arbitrarily
    //for all episodes
    //initialize s
    //choose a useing policy derived from Q
    //repeat
    //take action a, observe r and s'
    //update Q(s, a)
    //Q(s, a) = Q(s, a) + eta(r + gamma(Q(s', a') - Q(s, a))
    //s = s'
    //a = a'
    //until s is terminal state
}
