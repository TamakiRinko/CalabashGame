package model.controlled;

import control.utils.ConstValues;
import control.utils.FileUtils;
import model.basic.Bullet;
import model.basic.Picture;
import model.basic.Point;
import model.basic.Character;
import model.good.Calabash;
import model.enemy.SmallEnemy;
import model.enemy.Snake;
import model.good.GrandFather;

import java.util.List;

public class NMap {
//    private static Lock bufferLock = new ReentrantLock();
//    private static int[][]NMap = //原始Map
//            {{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
//            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
//            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
//            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
//            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
//            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
//            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
//            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
//            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
//            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
//            {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1},
//            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}};
//    public static final int N = 15;    //15个格子
    public final static Character[][]GameMap = new Character[ConstValues.N][ConstValues.N];

    private Picture picture1;
    private Picture picture2;

    public static boolean changeNMapLeft(Point point, Character character){
        boolean flag = false;
        synchronized (GameMap){
            if(notOccupiedLeft(point)){
                GameMap[point.getPx()][point.getPy()] = null;
                GameMap[point.getPx() - 1][point.getPy()] = character;
                flag = true;
            }
        }
        return flag;
    }
    public static boolean changeNMapRight(Point point, Character character){
        boolean flag = false;
        synchronized (GameMap){
            if(notOccupiedRight(point)){
                GameMap[point.getPx()][point.getPy()] = null;
                GameMap[point.getPx() + 1][point.getPy()] = character;
                flag = true;
            }
        }
        return flag;
    }
    public static boolean changeNMapUp(Point point, Character character){

        boolean flag = false;
        synchronized (GameMap){
            if(notOccupiedUp(point)){
                GameMap[point.getPx()][point.getPy()] = null;
                GameMap[point.getPx()][point.getPy() - 1] = character;
                flag = true;
            }
        }
        return flag;

//        bufferLock.lock();
//        NMap[point.getPx()][point.getPy() - 1] = 1;
//        NMap[point.getPx()][point.getPy()] = 0;
//        bufferLock.unlock();
    }
    public static boolean changeNMapDown(Point point, Character character){
        boolean flag = false;
        synchronized (GameMap){
            if(notOccupiedDown(point)){
                GameMap[point.getPx()][point.getPy()] = null;
                GameMap[point.getPx()][point.getPy() + 1] = character;
                flag = true;
            }
        }
        return flag;
    }

    //检测要走的路径是否被占用
    public static boolean notOccupiedLeft(Point point) {
        return (point.getPx() - 1 >= 0 && GameMap[point.getPx() - 1][point.getPy()] == null);
    }
    public static boolean notOccupiedRight(Point point){
        return (point.getPx() + 1 < ConstValues.N && GameMap[point.getPx() + 1][point.getPy()] == null);
    }
    public static boolean notOccupiedUp(Point point){
//        return (NMap[point.getPx()][point.getPy() - 1] == 0);
//        System.out.println("x = " + (point.getPx()) + ", y = " + (point.getPy() - 1) + "move: " + (point.getPy() - 1 >= 0 && GameMap[point.getPx()][point.getPy() - 1] == null));
        return (point.getPy() - 1 >= 0 && GameMap[point.getPx()][point.getPy() - 1] == null);
    }
    public static boolean notOccupiedDown(Point point){
        return (point.getPy() + 1 < ConstValues.N && GameMap[point.getPx()][point.getPy() + 1] == null);
    }

    public static void removeCharacter(Point point){
        synchronized (GameMap){
            GameMap[point.getPx()][point.getPy()] = null;
        }
    }

    public NMap(String srcPath1, String srcPath2){
        picture1 = new Picture(new Point(0, 0), FileUtils.getImage(srcPath1));
        picture2 = new Picture(new Point(0, 0), FileUtils.getImage(srcPath2));
    }

    public Picture getPicture1(){
        return picture1;
    }
    public Picture getPicture2(){
        return picture2;
    }

    public static void setGameMap(CalabashTeam calabashTeam, EnemyTeam enemyTeam){

        for(int i = 0; i < ConstValues.N; ++i){
            for(int j = 0; j < ConstValues.N; ++j){
                GameMap[i][j] = null;
            }
        }

        GrandFather grandFather = calabashTeam.getKeyCharacter();
        List<Calabash> calabashList = calabashTeam.getNormalTeam();
        GameMap[grandFather.getPx()][grandFather.getPy()] = grandFather;
        for(Calabash calabash: calabashList){
            GameMap[calabash.getPx()][calabash.getPy()] = calabash;
        }

        Snake snake = enemyTeam.getKeyCharacter();
        List<SmallEnemy> smallEnemyList = enemyTeam.getNormalTeam();
        GameMap[snake.getPx()][snake.getPy()] = snake;
        for(SmallEnemy smallEnemy: smallEnemyList){
            GameMap[smallEnemy.getPx()][smallEnemy.getPy()] = smallEnemy;
        }
    }

    public static boolean beyondBoundary(Point point){
        return (point.getPx() >= ConstValues.N || point.getPy() >= ConstValues.N || point.getPx() < 0 || point.getPy() < 0);
    }

    public static Character getEnemy(Bullet bullet, Point point){
        Character posCharacter;
        synchronized (GameMap){
            if(GameMap[point.getPx()][point.getPy()] == null)   return null;
            posCharacter = GameMap[point.getPx()][point.getPy()];
        }
        Character enemy = bullet.getTargetCharacter();
        if((enemy instanceof SmallEnemy && posCharacter instanceof SmallEnemy) ||
                (enemy instanceof Calabash && posCharacter instanceof Calabash)){
                return posCharacter;
        }
        return null;
    }

}
