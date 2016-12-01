/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wilson
 */
public class CollisionReset implements ICollisionHandler{
    public State handleCollision(State startState, Position collisionPosition){
        State state = new State();
        state.x = startState.x;
        state.y = startState.y;
        state.Vx = 0;
        state.Vy = 0;
        return state;
    }
}
