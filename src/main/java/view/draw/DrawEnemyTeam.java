package view.draw;

import model.enemy.SmallEnemy;
import model.controlled.EnemyTeam;
import javafx.scene.canvas.GraphicsContext;

public class DrawEnemyTeam extends DrawBase {
    private EnemyTeam enemyTeam;
    public DrawEnemyTeam(GraphicsContext graphicsContext, EnemyTeam enemyTeam) {
        super(graphicsContext);
        this.enemyTeam = enemyTeam;
    }

    @Override
    public void draw() {
        drawPic(enemyTeam.getKeyCharacter().getPicture());
        for(SmallEnemy smallEnemy: enemyTeam.getNormalTeam()){
            drawPic(smallEnemy.getPicture());
            drawRect(smallEnemy);
        }
    }
}
