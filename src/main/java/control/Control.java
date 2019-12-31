package control;

import control.factory.TeamFactory;
import control.utils.FileUtils;
import javafx.util.Pair;
import model.good.Calabash;
import model.enemy.SmallEnemy;
import model.enemy.Snake;
import model.basic.Point;
import model.controlled.*;
import model.good.GrandFather;

import java.util.List;

public class Control{
    private static final int ALARMGAP = 6000;
    private NMap nMap;                                              //地图
    private static CalabashTeam calabashTeam;                       //葫芦兄弟
    private static EnemyTeam enemyTeam;                             //敌人队伍


    private BulletList bulletList;

    public Control(){
        //初始化地图和各个人物
        nMap = new NMap("Map1", "Map2");
        bulletList = new BulletList();
        new Thread(bulletList).start();

        Pair<GrandFather, List<Calabash>> calabashPair = TeamFactory.generateCalabashTeam(FileUtils.getPointList("葫芦娃长蛇"));
        calabashTeam = new CalabashTeam(calabashPair.getKey(), calabashPair.getValue());
        Pair<Snake, List<SmallEnemy>> enemyPair = TeamFactory.generateEnemyTeam(FileUtils.getPointList("敌人长蛇"));
        enemyTeam = new EnemyTeam(enemyPair.getKey(), enemyPair.getValue());

        NMap.setGameMap(calabashTeam, enemyTeam);

    }

    public void changeCalabashTeamFormation(List<Point> formation){
        calabashTeam.changeFormation(formation);
    }

    public void changeEnemyTeamFormation(List<Point> formation){
        enemyTeam.changeFormation(formation);
    }

    public void start(){
        calabashTeam.start();
        enemyTeam.start();
        bulletList.start();
    }

    public void suspend(){
        calabashTeam.suspend();
        enemyTeam.suspend();
        bulletList.suspend();
    }

    public NMap getnMap() {
        return nMap;
    }

    public static CalabashTeam getCalabashTeam() {
        return calabashTeam;
    }

    public static EnemyTeam getEnemyTeam() {
        return enemyTeam;
    }
}
