package model.controlled;

import control.utils.ConstValues;
import model.basic.Bullet;
import model.basic.Character;
import model.basic.Point;
import model.bullet.IceBullet;

import java.util.ArrayList;
import java.util.List;

public class BulletList implements Runnable{
    public static final List<Bullet> bulletList = new ArrayList<>();
    private boolean stopFlag = true;
    private final String control = "";
    private boolean isDead = false;

    public static void add(Bullet bullet){
        synchronized(bulletList){
            bulletList.add(bullet);
        }
    }

    public static void remove(Bullet bullet){
        synchronized (bulletList){
            bulletList.remove(bullet);
        }
    }

    public void start(){
        setStopFlag(false);
    }

    public void suspend(){
        setStopFlag(true);
    }

    @Override
    public void run() {
        while (!isDead){
            synchronized (control) {
                if (stopFlag) {
                    try {
                        control.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            synchronized (bulletList){
                for(int i = 0; i < bulletList.size();){
                    Bullet bullet = bulletList.get(i);
                    bullet.move();
                    Point bulletPoint = getPoint(bullet);
                    if(NMap.beyondBoundary(bulletPoint)){
                        bulletList.remove(i);
                    }else{
                        Character enemy = NMap.getEnemy(bullet, bulletPoint);
                        if(enemy != null){
                            if(bullet instanceof IceBullet){
                                enemy.frozen();
                            }
                            enemy.attacked(bullet.getAttackPower());
                            bulletList.remove(i);
                        }else{
                            i++;
                        }
                    }
                }
//                System.out.println(bulletList.size());
            }

            try {
                Thread.sleep(ConstValues.BulletMoveInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setStopFlag(boolean flag) {
        if (!flag) {
            synchronized (control) {
                control.notifyAll();
            }
        }
        this.stopFlag = flag;
    }


    private Point getPoint(Bullet bullet){
//        System.out.println("x = " + bullet.getX() + ", y = " + bullet.getY());
        return new Point((int)(bullet.getX() / ConstValues.PictureWidth), (int)(bullet.getY() / ConstValues.PictureHeight));
    }
}
