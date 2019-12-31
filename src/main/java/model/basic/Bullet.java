package model.basic;

import com.sun.org.apache.regexp.internal.RE;
import javafx.scene.image.Image;

import java.io.Serializable;

public class Bullet implements Serializable {
    private transient Image image;
    private int attackPower;
    private double x;                                   //地图上的位置，非Point
    private double y;
    private transient double speedX;                              //x方向子弹前进的速度
    private transient double speedY;                              //y方向

    private Character targetCharacter;                  //攻击的对象

    public Bullet(Image image, int attackPower, double x, double y, double speedX, double speedY, Character targetCharacter) {
        this.image = image;
        this.attackPower = attackPower;
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.targetCharacter = targetCharacter;
    }

    public Bullet(Bullet bullet) {
        this.attackPower = bullet.attackPower;
        this.x = bullet.x;
        this.y = bullet.y;
    }

    public Image getImage() {
        return image;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getAttackPower(){
        return attackPower;
    }

    public Character getTargetCharacter() {
        return targetCharacter;
    }

    public void move(){
        x = x + speedX;
        y = y + speedY;
    }

    public void setImage(Image image){
        this.image = image;
    }
}
