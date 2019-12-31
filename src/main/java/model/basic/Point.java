package model.basic;

import java.io.Serializable;

public class Point implements Serializable {
    private int px;
    private int py;

    /**
     * 重写equals方法
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        if (this == obj){
            return true;
        }
        if (obj instanceof Point){
            Point o = (Point) obj;
            return (this.px == o.getPx() && this.py == o.getPy());
        }
        return false;
    }

    public Point(int px, int py) {
        this.px = px;
        this.py = py;
    }

    public int getPx() {
        return px;
    }

    public int getPy() {
        return py;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public void setPy(int py) {
        this.py = py;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        return obj;
    }
}
