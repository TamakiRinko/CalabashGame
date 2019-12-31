package model.enemy;

import control.Control;
import model.basic.Character;
import javafx.scene.image.Image;
import model.good.Calabash;
import model.controlled.CalabashTeam;
import model.controlled.NMap;

import java.util.List;

public class SmallEnemy extends Character {
    public SmallEnemy(int blood, int px, int py, Image image){
        super(blood, px, py, image);
    }

    public SmallEnemy(SmallEnemy smallEnemy) {
        super(smallEnemy);
    }

    @Override
    public void run() {
        super.run();
    }

    public void selectOneEnemy(){
        CalabashTeam calabashTeam = Control.getCalabashTeam();
        synchronized (Control.getCalabashTeam()){
            List<Calabash> calabashList = calabashTeam.getNormalTeam();
            if(calabashList.size() > 0){
                int index = random.nextInt(calabashList.size());
                Calabash calabash = calabashList.get(index);
                if(!calabash.getIsDead()){
                    targetEnemy = calabash;
                }
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
