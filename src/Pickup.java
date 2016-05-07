import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * Created by marc on 8/22/15.
 */
public abstract class Pickup extends CollisionUnit {
    private Pane level;
    private ImageView imageView;

    public Pickup(Pane level, int x, int y, int height, int width) {
        super(x, y, height, width);
        this.level = level;
    }

    public Pane getLevel() {
        return this.level;
    }

    public void setLevel(Pane level) {
        this.level = level;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void setImageView(ImageView view) {
        this.imageView = view;
    }

    public void onPickup() {
        this.level.getChildren().removeAll(this.getImageView());
    }
}
