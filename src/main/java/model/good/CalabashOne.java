package model.good;

import control.factory.BulletFactory;
import control.utils.ConstValues;
import javafx.scene.image.Image;
import model.basic.Color;
import model.basic.Direction;
import model.basic.Rank;
import model.controlled.BulletList;

/**
 * 大娃二连发
 */
//@CharacterAttributes()
public class CalabashOne extends Calabash {

    public CalabashOne(int blood, int px, int py, Color color, Rank rank, Image image) {
        super(blood, px, py, color, rank, image);
    }

//    public void getAttr(){
//    }

    /**
     * 一次同方向发两个子弹
     */
    @Override
    public void run() {

        new Thread(() -> {
            while (!isDead){
                synchronized (threadControl) {
                    if (stopFlag) {
                        try {
                            threadControl.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                sleep(ConstValues.CharacterMoveInterval);
                direction = Direction.randomFourDirection();
                move();
            }
        }).start();

        while (!isDead){

            synchronized (threadControl) {
                if (stopFlag) {
                    try {
                        threadControl.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            selectOneEnemy();
            BulletList.add(BulletFactory.generateBullet(this, targetEnemy));
            sleep(210);
            BulletList.add(BulletFactory.generateBullet(this, targetEnemy));
            sleep(6000);
        }
    }
}
