package model.enemy;

import control.Control;
import javafx.scene.image.Image;
import model.good.Calabash;
import model.controlled.CalabashTeam;
import model.controlled.NMap;

import java.util.List;

public class Scorpion extends SmallEnemy {
    public Scorpion(int blood, int px, int py, Image image){
        super(blood, px, py, image);
    }

    public void selectOneEnemy(){
        CalabashTeam calabashTeam = Control.getCalabashTeam();
        if(calabashTeam.getNormalTeam().size() <= 0)    return;
        synchronized (Control.getCalabashTeam()){
            List<Calabash> calabashList = calabashTeam.getNormalTeam();
            int index = random.nextInt(calabashList.size());
            Calabash calabash = calabashList.get(index);
            if(!calabash.getIsDead()){
                targetEnemy = calabash;
            }
        }
    }

    public void attacked(int damage){
        synchronized (bloodControl){
            blood = blood - damage;
            if(blood <= 0){
                isDead = true;
                NMap.removeCharacter(getPoint());
                Control.getEnemyTeam().removeCharacter(this);
            }
        }
    }

}
