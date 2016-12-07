//Returns to agents previous position with velocity set to 0

//If we pass the previous position to the handler, all we should have to do is retrieve the correct state for that position with velocity 0, right?
public class CollisionStop implements ICollisionHandler {

    @Override
    public State handleCollision(State startState, Position prevPos) {

        return new State(prevPos, new Velocity(0, 0));   
//im not sure, but i think we just want to set the agent back to its previous spot and 0 its velocity?  This still isnt quite how we want to do this since we dont want to instantiate new state, but should work for now? maybe

    }
}
