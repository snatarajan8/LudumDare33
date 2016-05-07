import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wall extends CollisionUnit {

    private ImageView view;

    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
        Image image = new Image(getClass().getResource("resources/foreground.png").toExternalForm());
        view = new ImageView(image);
    }

    public ImageView getImageView() {
        return view;
    }
}
