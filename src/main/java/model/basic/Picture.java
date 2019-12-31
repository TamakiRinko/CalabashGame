package model.basic;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Picture implements Serializable, Cloneable {
    private transient Image image;  //图片
    private Point point;  //坐标
    private int width;        //每个格子的长度
    private int height;       //每个格子的宽度
    public Picture(Point point, Image image){
//        this.point = new Point(point.getPx(), point.getPy());
        this.point = point;
        this.image = image;
        width = (int)image.getWidth();
        height = (int)image.getHeight();
    }

    public Image getImage() {
        return image;
    }

    public Point getPoint() {
        return point;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPoint(Point point) {
        this.point.setPx(point.getPx());
        this.point.setPy(point.getPy());
    }

    public void setImage(Image image){
        this.image = image;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        ((Picture)obj).point = (Point) this.point.clone();
        return obj;
    }
}
