import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Created by marc on 8/22/15.
 */
public class BonePile extends Pickup {
    private ImageView image;
    public BonePile(Pane level, int x, int y, int height, int width) {
        super(level, x, y, height, width);
        //SET IMAGE VIEW HERE this.setImageView();
        image = new ImageView("resources/pickup/BonePile.png");
    }

    public void onPickup(Player player) {
        super.onPickup();
        //HEAL PLAYER HERE

    }
}
