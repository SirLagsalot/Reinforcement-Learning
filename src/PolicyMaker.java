/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wilson
 */
public class PolicyMaker {
    StateIDMapper iDMap;
    char[][] track;
    TrackSimulator simulator;
    
    public PolicyMaker(StateIDMapper map, char[][] track, TrackSimulator sim){
        iDMap = map;
        this.track = track;
        simulator = sim;
    }
    int[] createPolicy(){
        return new int[0];
    }
}
