import javafx.scene.Scene;
import java.awt.Rectangle;

public class EnemyBat extends Character {

    private int distance; // make multiple of movementFactor
    private int travelled;
    private Rectangle vision;
    private final int attackCooldown = 20;
    private int count;
    private boolean canSeePlayer = false;
    private final int maxVelocity = 20;

    public EnemyBat (Game game, int x, int y, int height, int width, int patrolDistance) {
        super(game, x, y, height, width);
        movementFactor = 15;
        initAnimation();
        distance = patrolDistance;
        vision = new Rectangle(x, y, height, width + 50);
        count = 0;
    }

    private void initAnimation() {
        animation = new SpriteAnimation();
        animation.addSequence(State.MOVELEFT, animArray("resources/Bat/MOVELEFT", 4));
        animation.addSequence(State.MOVERIGHT, animArray("resources/Bat/MOVERIGHT", 4));

    }

    public void update(Player player) {
        super.update();
        if (!canSeePlayer) {
            if (vision.intersects(player.rectangle) || vision.contains(player.rectangle)) {
                canSeePlayer = true;
            }
        }

        if (canSeePlayer) {
            if (Math.abs(vision.getX()-player.rectangle.getX()) > 0 && velocity.horizontal < 0) {
                moveHorizontal(true, true);
                moveHorizontal(true, true);
            } else if (Math.abs(vision.getX()-player.rectangle.getX()) < 0 && velocity.horizontal > 0) {
                moveHorizontal(false, true);
                moveHorizontal(false, true);
            }
            if (Math.abs(velocity.horizontal) > maxVelocity) {
                velocity.horizontal = maxVelocity;
            }
        }
        travelled += Math.abs(velocity.horizontal);
        if (travelled == distance) {
            moveHorizontal(left, true);
            moveHorizontal(left, true);
            travelled = 0;
        }
    }

    public void attack() {
        if (canSeePlayer && count == attackCooldown) {
            count = 0;

        }
        count++;
    }



}
