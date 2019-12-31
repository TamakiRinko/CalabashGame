package model.basic;

import control.factory.TeamFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BasicTeam <T1 extends KeyCharacter, T2 extends Character> implements Serializable {
    protected T1 keyCharacter;                                        //关键人物，老爷爷或蛇精
    protected List<T2> normalTeam;                                    //普通队伍

    public BasicTeam(T1 keyCharacter, List<T2> normalTeam){
        this.keyCharacter = keyCharacter;
        this.normalTeam = normalTeam;
    }

    public BasicTeam(){}

    public void removeCharacter(Character character){
        synchronized (normalTeam){
            normalTeam.remove(character);
        }
    }

    public void start(){
        keyCharacter.setStopFlag(false);
        for (T2 normal : normalTeam) {
            normal.setStopFlag(false);
        }
    }

    public void suspend(){
        keyCharacter.setStopFlag(true);
        for (T2 normal : normalTeam) {
            normal.setStopFlag(true);
        }
    }

    /**
     * 改变阵型
     * @param pointList
     */
    public void changeFormation(List<Point> pointList){
        int movNum = (pointList.size()) > normalTeam.size()?normalTeam.size():(pointList.size());
        //每个人得到一个新的坐标
        for(int i = 0; i < movNum; ++i){
            normalTeam.get(i).setPoint(pointList.get(i).getPx(), pointList.get(i).getPy());
        }
    }

    public List<T2> getNormalTeam(){
        return normalTeam;
    }

    public T1 getKeyCharacter(){
        return keyCharacter;
    }
}
