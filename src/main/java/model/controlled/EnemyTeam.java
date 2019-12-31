package model.controlled;

import model.basic.BasicTeam;
import model.enemy.SmallEnemy;
import model.enemy.Snake;

import java.util.ArrayList;
import java.util.List;

public class EnemyTeam extends BasicTeam<Snake, SmallEnemy> {

    public EnemyTeam(Snake snake, List<SmallEnemy> smallEnemyList){
        super(snake, smallEnemyList);

        new Thread(snake).start();
        for (SmallEnemy smallEnemy : normalTeam) {
            new Thread(smallEnemy).start();
        }
    }

    public EnemyTeam(EnemyTeam enemyTeam){
//        keyCharacter = (T1) basicTeam.keyCharacter.clone();
        this.keyCharacter = new Snake(enemyTeam.keyCharacter);
        this.normalTeam = new ArrayList<>();
        for(SmallEnemy smallEnemy: enemyTeam.normalTeam){
            this.normalTeam.add(new SmallEnemy(smallEnemy));
        }
    }
}
