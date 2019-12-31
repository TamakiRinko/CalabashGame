package view.draw;
import control.utils.ConstValues;
import model.controlled.NMap;
import javafx.scene.canvas.GraphicsContext;

public class DrawMap extends DrawBase {
    NMap nMap;
    public DrawMap(GraphicsContext graphicsContext, NMap nMap) {
        super(graphicsContext);
        this.nMap = nMap;
    }

    @Override
    public void draw() {
        for (int i = 0; i < ConstValues.N; i++) {
            for (int j = 0; j < ConstValues.N; j++) {
                if((i + j) % 2 == 0){
                    graphicsContext.drawImage(this.nMap.getPicture1().getImage(),
                            0, 0, this.nMap.getPicture1().getWidth(), this.nMap.getPicture1().getHeight(),
                            j * this.nMap.getPicture1().getWidth(), i * this.nMap.getPicture1().getHeight()
                            , this.nMap.getPicture1().getWidth(), this.nMap.getPicture1().getHeight());
                }else{
                    graphicsContext.drawImage(this.nMap.getPicture2().getImage(),
                            0, 0, this.nMap.getPicture2().getWidth(), this.nMap.getPicture2().getHeight(),
                            j * this.nMap.getPicture2().getWidth(), i * this.nMap.getPicture2().getHeight()
                            , this.nMap.getPicture2().getWidth(), this.nMap.getPicture2().getHeight());
                }
            }
        }
    }
}
