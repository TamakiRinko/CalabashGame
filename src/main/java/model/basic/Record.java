package model.basic;

import control.utils.FileUtils;
import model.bullet.IceBullet;
import model.good.Calabash;
import model.controlled.CalabashTeam;
import model.controlled.EnemyTeam;
import model.enemy.SmallEnemy;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Record implements Serializable {
    private static final long serialVersionUID=7943290979140871866l;

    private CalabashTeam calabashTeam;              //葫芦娃队伍
    private EnemyTeam enemyTeam;                    //敌人队伍
    private List<Bullet> bulletList;                //子弹列表

    public Record(CalabashTeam calabashTeam, EnemyTeam enemyTeam, List<Bullet> bulletList){
        this.calabashTeam = new CalabashTeam(calabashTeam);
        this.enemyTeam = new EnemyTeam(enemyTeam);
        this.bulletList = new ArrayList<>();
        for(Bullet bullet: bulletList){
            this.bulletList.add(new Bullet(bullet));
        }
    }

    public CalabashTeam getCalabashTeam() {
        return calabashTeam;
    }

    public EnemyTeam getEnemyTeam() {
        return enemyTeam;
    }

    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setImage() throws IOException {
        for(Calabash calabash: calabashTeam.normalTeam){
            int id = calabash.getId();
            switch(id){
                case 0: FileUtils.setImage(calabash, "CalabashOne");    break;
                case 1: FileUtils.setImage(calabash, "CalabashTwo");    break;
                case 2: FileUtils.setImage(calabash, "CalabashThree");    break;
                case 3: FileUtils.setImage(calabash, "CalabashFour");    break;
                case 4: FileUtils.setImage(calabash, "CalabashFive");    break;
                case 5: FileUtils.setImage(calabash, "CalabashSix");    break;
                case 6: FileUtils.setImage(calabash, "CalabashSeven");    break;
                default: break;
            }
        }
        FileUtils.setImage(calabashTeam.getKeyCharacter(), "GrandFather");

        for(SmallEnemy smallEnemy: enemyTeam.getNormalTeam()){
            if(smallEnemy.getFullBlood() == 15){
                FileUtils.setImage(smallEnemy, "Scorpion");
            }else {
                FileUtils.setImage(smallEnemy, "SmallEnemy");
            }
        }
        FileUtils.setImage(enemyTeam.getKeyCharacter(), "Snake");
//        synchronized (bulletList){
            for(Bullet bullet: bulletList){
                if(bullet.getAttackPower() == 3){
                    FileUtils.setBulletImage(bullet, "IceBullet");
                }else{
                    FileUtils.setBulletImage(bullet, "Bullet");
                }
            }
//        }

    }

}
