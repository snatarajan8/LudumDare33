import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by marc on 8/22/15.
 */
public abstract class Breakable extends CollisionUnit {
    private Pane level;
    private ImageView imageView;

    public Breakable(Pane level, int x, int y, int height, int width) {
        super(x, y, height, width);
        this.level = level;
    }
    public void onBreak() {
        this.level.getChildren().removeAll(this.getImageView());
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

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
