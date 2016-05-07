import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

/**
 * Created by marc on 8/22/15.
 */
public class Boulder extends Breakable {

    public Boulder(Pane level, int x, int y, int height, int width) {
        super(level, x, y, height, width);
        //this.setImageView() = SET IMAGE VIEW HERE
    }

    @Override
    public void onBreak() {
        super.onBreak();
        //do anything else here
    }
}
