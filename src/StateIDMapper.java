
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Wilson
 */
public class StateIDMapper {
    ArrayList<StateInfo> stateInfos = new ArrayList<StateInfo>();
    
    public State GetStateFromID(int stateID){
        StateInfo info = getStateInfoFromID(stateID);
        int stateOffset = stateID-info.stateID;
        //TODO
    }
    
    private StateInfo getStateInfoFromID(int stateID){
        if(stateID >= stateInfos.get(stateInfos.size()-1).stateID)
            return stateInfos.get(stateInfos.size()-1);
        
        int currentIndex;
        currentIndex = (stateInfos.size() - 1) / 2;
        int maxBound = stateInfos.size() - 1;
        int minBound = 0;
        while(minBound >= 0){
            StateInfo curInfo = stateInfos.get(currentIndex);
            if(curInfo.stateID <= stateID){
                if(stateInfos.get(currentIndex+1).stateID > stateID){
                    return curInfo;
                }
                else{
                    minBound = currentIndex;
                    currentIndex = (maxBound+minBound)/2;
                }
            }
            else{
                maxBound = currentIndex;
                currentIndex = (maxBound+minBound)/2;
            }
        }
        return new StateInfo();
    }
    
    public int getStateIDFromState(State state){
        StateInfo info = getStateInfoFromPosition(state.position);
        return info.stateID + (state.Vx - info.minVelocityX)*(info.maxVelocityY-info.minVelocityY) + state.Vy - info.minVelocityY;
    }
    
    public StateInfo getStateInfoFromPosition(Position pos){
        for(StateInfo info : stateInfos){
            if(info.position.x == pos.x && info.position.y == pos.y){
                return info;
            }
        }
        return new StateInfo();
    }
    
    public class StateInfo{
        int stateID;
        int minVelocityX;
        int maxVelocityX;
        int minVelocityY;
        int maxVelocityY;
        Position position;
    }
}
