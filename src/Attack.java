import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//just going to commit this to see if my ssh key works
public class Attack extends CollisionUnit {

    public static final int ATTACK_WIDTH = 10;
    public static final int ATTACK_HEIGHT = 10;

    private ImageView view;
    private int count;

    public Attack(int x, int y) {
        super(x, y, ATTACK_WIDTH, ATTACK_HEIGHT);
        Image image = new Image(getClass().getResource("resources/player/FACELEFT1.png").toExternalForm());
        view = new ImageView(image);
        count = 0;
    }

    public ImageView getImageView() {
        return view;
    }

    public void incrementDuration() {
        count += 1;
    }

    public int getDuration() {
        return count;
    }
}
