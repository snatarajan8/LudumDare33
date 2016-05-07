import java.awt.Rectangle;
public class EnemyWerewolf extends Character {

    public static final int WOLF_WIDTH = 20;
    public static final int WOLF_HEIGHT = 20;

    private int distance;
    private int travelled;
    private Rectangle vision;
    private final int attackCooldown = 20;
    private int count;
    private boolean canSeePlayer = false;
    private int maxVelocity = 20;

    public EnemyWerewolf(Game game, int x, int y, int patrolArea) {
        super(game, x, y, WOLF_WIDTH, WOLF_HEIGHT);
        distance = patrolArea;
        travelled = 0;
        initAnimation();
    }

    private void initAnimation() {
        animation = new SpriteAnimation();
        animation.addSequence(State.FACELEFT, animArray("resources/Werewolf/FACELEFT", 3));
        animation.addSequence(State.FACERIGHT, animArray("resources/Werewolf/FACERIGHT", 3));
        animation.addSequence(State.MOVELEFT, animArray("resources/Werewolf/MOVELEFT", 6));
        animation.addSequence(State.MOVERIGHT, animArray("resources/Werewolf/MOVERIGHT", 6));
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
        if (travelled >= distance) {
            moveHorizontal(left, true);
            moveHorizontal(left, true);
            travelled = 0;
        }
    }
}
