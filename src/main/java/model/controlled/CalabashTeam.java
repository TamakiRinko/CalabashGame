package model.controlled;

import model.good.Calabash;
import model.basic.BasicTeam;
import model.good.GrandFather;

import java.util.ArrayList;
import java.util.List;

public class CalabashTeam extends BasicTeam<GrandFather, Calabash> {

    public CalabashTeam(GrandFather grandFather, List<Calabash> calabashList){
        super(grandFather, calabashList);
        new Thread(grandFather).start();
        for (Calabash calabash : normalTeam) {
            new Thread(calabash).start();
        }
    }

    public CalabashTeam(CalabashTeam calabashTeam){
//        keyCharacter = (T1) basicTeam.keyCharacter.clone();
        this.keyCharacter = new GrandFather(calabashTeam.keyCharacter);
        this.normalTeam = new ArrayList<>();
        for(Calabash calabash: calabashTeam.normalTeam){
            this.normalTeam.add(new Calabash(calabash));
        }
    }

}
