package model.good;

import control.Control;
import control.utils.ConstValues;
import model.basic.KeyCharacter;
import model.basic.Picture;
import javafx.scene.image.Image;

public class GrandFather extends KeyCharacter {
    private Picture goodPicture;
    public GrandFather(int blood, int px, int py, Image image, Picture goodPicture){
        super(blood, px, py, image);
        this.goodPicture = goodPicture;
    }

    public GrandFather(GrandFather grandFather){
        super(grandFather);
    }

    public Picture getGoodPicture(){
        return goodPicture;
    }

    @Override
    public void run() {

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

            for(Calabash calabash: Control.getCalabashTeam().getNormalTeam()){
                calabash.healed(1);
            }

            sleep(ConstValues.HearInterval);
        }
    }
}
