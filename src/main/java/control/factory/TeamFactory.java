package control.factory;

import control.utils.FileUtils;
import javafx.util.Pair;
import model.annotation.CharacterAttributes;
import model.good.*;
import model.enemy.Scorpion;
import model.enemy.SmallEnemy;
import model.enemy.Snake;
import model.basic.*;
import model.good.GrandFather;

import java.util.ArrayList;
import java.util.List;

public class TeamFactory{
    @CharacterAttributes
    private static int calabashOneAttr;
    @CharacterAttributes(blood = 4)
    private static int calabashTwoAttr;
    @CharacterAttributes(blood = 12)
    private static int calabashThreeAttr;
    @CharacterAttributes
    private static int calabashFourAttr;
    @CharacterAttributes
    private static int calabashFiveAttr;
    @CharacterAttributes
    private static int calabashSixAttr;
    @CharacterAttributes
    private static int calabashSevenAttr;
    @CharacterAttributes(blood = 8)
    private static int smallEnemyAttr;
    @CharacterAttributes(blood = 15)
    private static int scorpionAttr;
    @CharacterAttributes(blood = 20)
    private static int grandFatherAttr;
    @CharacterAttributes(blood = 20)
    private static int snakeAttr;


    public static Pair<GrandFather, List<Calabash>> generateCalabashTeam(List<Point> pointList){
        Picture goodPicture = new Picture(new Point(1, 0), FileUtils.getImage("GrandFatherCheer"));
        GrandFather grandFather = new GrandFather(getBlood("grandFatherAttr"), 0, 0, FileUtils.getImage("GrandFather"), goodPicture);
        List<Calabash> calabashList = new ArrayList<>();

        CalabashOne calabashOne = new CalabashOne(getBlood("calabashOneAttr"), pointList.get(0).getPx(), pointList.get(0).getPy(),
                Color.values()[0], Rank.values()[0], FileUtils.getImage("CalabashOne"));
        calabashList.add(calabashOne);

        CalabashTwo calabashTwo = new CalabashTwo(getBlood("calabashTwoAttr"), pointList.get(1).getPx(), pointList.get(1).getPy(),
                Color.values()[1], Rank.values()[1], FileUtils.getImage("CalabashTwo"));
        calabashList.add(calabashTwo);

        CalabashThree calabashThree = new CalabashThree(getBlood("calabashThreeAttr"), pointList.get(2).getPx(), pointList.get(2).getPy(),
                Color.values()[2], Rank.values()[2], FileUtils.getImage("CalabashThree"));
        calabashList.add(calabashThree);

        CalabashFour calabashFour = new CalabashFour(getBlood("calabashFourAttr"), pointList.get(3).getPx(), pointList.get(3).getPy(),
                Color.values()[3], Rank.values()[3], FileUtils.getImage("CalabashFour"));
        calabashList.add(calabashFour);

        CalabashFive calabashFive = new CalabashFive(getBlood("calabashFiveAttr"), pointList.get(4).getPx(), pointList.get(4).getPy(),
                Color.values()[4], Rank.values()[4], FileUtils.getImage("CalabashFive"));
        calabashList.add(calabashFive);

        CalabashSix calabashSix = new CalabashSix(getBlood("calabashSixAttr"), pointList.get(5).getPx(), pointList.get(5).getPy(),
                Color.values()[5], Rank.values()[5], FileUtils.getImage("CalabashSix"));
        calabashList.add(calabashSix);

        CalabashSeven calabashSeven = new CalabashSeven(getBlood("calabashSevenAttr"), pointList.get(6).getPx(), pointList.get(6).getPy(),
                Color.values()[6], Rank.values()[6], FileUtils.getImage("CalabashSeven"));
        calabashList.add(calabashSeven);

        return new Pair<>(grandFather, calabashList);
    }

    public static Pair<Snake, List<SmallEnemy>> generateEnemyTeam(List<Point> pointList){
        Picture orderPicture = new Picture(new Point(12, 0), FileUtils.getImage("SnakeCheer"));
        Snake snake = new Snake(getBlood("snakeAttr"), 14, 0, FileUtils.getImage("Snake"), orderPicture);

        List<SmallEnemy> smallEnemyList = new ArrayList<>();
        Scorpion scorpion = new Scorpion(getBlood("scorpionAttr"), pointList.get(0).getPx(), pointList.get(0).getPy(), FileUtils.getImage("Scorpion"));

        smallEnemyList.add(scorpion);
        for(int i = 1; i < pointList.size(); ++i){
            SmallEnemy smallEnemy = new SmallEnemy(getBlood("smallEnemyAttr"), pointList.get(i).getPx(), pointList.get(i).getPy(),
                    FileUtils.getImage("SmallEnemy"));
            smallEnemyList.add(smallEnemy);
        }
        return new Pair<>(snake, smallEnemyList);
    }

    private static int getBlood(String fieldName){
        CharacterAttributes characterAttributes = null;
        try {
            characterAttributes = TeamFactory.class.getDeclaredField(fieldName).getAnnotation(CharacterAttributes.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        int blood = characterAttributes.blood();
        return blood;
    }
}
