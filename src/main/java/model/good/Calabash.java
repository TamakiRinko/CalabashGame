package model.good;

import control.Control;
import model.basic.Character;
import model.basic.Color;
import model.basic.Rank;
import javafx.scene.image.Image;
import model.controlled.EnemyTeam;
import model.controlled.NMap;
import model.enemy.SmallEnemy;

import java.util.List;

/**
 * 葫芦娃类
 */
public class Calabash extends Character {
    private int id;
    private Color color;
    private Rank rank;

    /**
     * 不要使用id进行初始化，葫芦娃出生时应该给予颜色和排行
     * @param color
     * @param rank
     * @param image
     */
    public Calabash(int blood, int px, int py, Color color, Rank rank, Image image){
        super(blood, px, py, image);
        this.color = color;
        this.rank = rank;
        this.id = rank.ordinal();
    }

    public Calabash(Calabash calabash) {
        super(calabash);
        this.color = calabash.color;
        this.rank = calabash.rank;
        this.id = rank.ordinal();
    }

    public int getId(){
        return this.id;
    }
    public Color getColor(){
        return this.color;
    }
    public Rank getRank(){
        return this.rank;
    }

    /**
     * 报数
     * @param isColor   true时按颜色报数，否则按排行报数
     */
    public void shout(boolean isColor){
        if(isColor){
            System.out.print(this.color + " ");
        }else{
            System.out.print(this.rank + " ");
        }
    }

    public void selectOneEnemy(){
        EnemyTeam enemyTeam = Control.getEnemyTeam();
        synchronized (Control.getEnemyTeam()){
            List<SmallEnemy> smallEnemyList = enemyTeam.getNormalTeam();
            if(smallEnemyList.size() > 0){
                int index = random.nextInt(smallEnemyList.size());
                SmallEnemy smallEnemy = smallEnemyList.get(index);
                if(!smallEnemy.getIsDead()){
                    targetEnemy = smallEnemy;
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
                Control.getCalabashTeam().removeCharacter(this);
            }
        }
    }

}
