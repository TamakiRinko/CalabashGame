package model.basic;

import control.Control;
import control.factory.BulletFactory;
import control.utils.ConstValues;
import model.controlled.BulletList;
import model.move.*;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Random;

public class Character implements Runnable, Serializable {
    protected Picture picture;
    protected Point point;
    private transient MoveLeft moveLeft;
    private transient MoveRight moveRight;
    private transient MoveUp moveUp;
    private transient MoveDown moveDown;
    private transient MoveServer moveServer;

    protected int blood;                                    //剩余血量
    protected int fullBlood;                                //总血量

    protected transient boolean stopFlag = true;
    protected transient final String threadControl = "control";
    protected transient final String bloodControl = "bloodControl";

    public boolean getIsDead() {
        return isDead;
    }

    protected transient boolean isDead;
    protected transient Character targetEnemy;                          //目标攻击人物
    protected transient Random random = new Random();
    protected transient Direction direction;                          //移动方向

    public Character(int blood, int px, int py, Image image){
        this.point = new Point(px, py);
        this.picture = new Picture(this.point, image);
        this.blood = blood;
        this.fullBlood = blood;
        this.moveServer = new MoveServer();
        this.moveLeft = new MoveLeft();
        this.moveRight = new MoveRight();
        this.moveUp = new MoveUp();
        this.moveDown = new MoveDown();
        this.targetEnemy = null;
        this.isDead = false;
    }

    public Character(Character character){
        this.point = new Point(character.getPx(), character.getPy());
        this.picture = new Picture(this.point, character.getImage());
        this.blood = character.blood;
        this.fullBlood = character.fullBlood;
    }

    public int getPx(){
        return this.point.getPx();
    }
    public int getPy() { return this.point.getPy(); }
    public Point getPoint(){
        return point;
    }
    public Image getImage(){
        return this.picture.getImage();
    }
    public void setImage(Image image){
        this.picture.setImage(image);
    }
    public Picture getPicture(){
        return this.picture;
    }
    public void setPoint(int Px, int Py){
        point.setPx(Px);
        point.setPy(Py);
    }
    public void setBlood(int blood) {
        this.blood = blood;
    }
    public int getFullBlood(){
        return fullBlood;
    }

    public double remainBloodPercentage(){
        return (1.0 * (double) blood) / (double) fullBlood;
    }

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

            sleep(ConstValues.CharacterGenerateBulletInterval);

        }
    }
    public void setStopFlag(boolean flag) {
        if (!flag) {
            synchronized (threadControl) {
                threadControl.notifyAll();
            }
        }
        this.stopFlag = flag;
    }

    protected void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void move(){
        switch (direction){
            case UP:{
                moveServer.move(moveUp, this.point, this);
                break;
            }
            case DOWN:{
                moveServer.move(moveDown, this.point, this);
                break;
            }
            case LEFT:{
                moveServer.move(moveLeft, this.point, this);
                break;
            }
            case RIGHT:{
                moveServer.move(moveRight, this.point, this);
                break;
            }
            default: break;
        }
    }

    public void selectOneEnemy(){
    }

    public void attacked(int damage){
    }

    public void frozen(){
        new Thread(() -> {
            stopFlag = true;
            sleep(ConstValues.FrozenInterval);
            stopFlag = false;
            synchronized (threadControl) {
                threadControl.notifyAll();
            }
        }).start();
    }

    public void healed(int healBlood){
        synchronized (bloodControl){
            if(blood + healBlood >= fullBlood){
                blood = fullBlood;
            }else{
                blood  = blood + healBlood;
            }
        }
    }

}
