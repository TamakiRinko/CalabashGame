package view.draw;

import model.good.Calabash;
import model.controlled.CalabashTeam;
import javafx.scene.canvas.GraphicsContext;

public class DrawCalabashTeam extends DrawBase {
    private CalabashTeam calabashTeam;                      //葫芦兄弟
    public DrawCalabashTeam(GraphicsContext graphicsContext, CalabashTeam calabashTeam) {
        super(graphicsContext);
        this.calabashTeam = calabashTeam;
    }

    @Override
    public void draw() {
        drawPic(calabashTeam.getKeyCharacter().getPicture());
        for(Calabash calabash: calabashTeam.getNormalTeam()){
            drawPic(calabash.getPicture());
            drawRect(calabash);
        }
    }
}
