import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class Player extends Character {

    public static final int PLAYER_WIDTH = 10;
    public static final int PLAYER_HEIGHT = 10;
    public static final int ATTACK_SPACE = 10;

    boolean secondJump;

    public Player (Game game, int x, int y) {
        super(game, x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
        initAnimation();
        secondJump = false;
    }

    public ImageView getImageView() {
        return animation.getImageView();
    }

    private void initAnimation() {
        animation = new SpriteAnimation();
        animation.addSequence(State.FACELEFT, animArray("resources/player/FACELEFT", 3));
        animation.addSequence(State.FACERIGHT, animArray("resources/player/FACERIGHT", 3));
        animation.addSequence(State.MOVELEFT, animArray("resources/player/MOVELEFT", 6));
        animation.addSequence(State.MOVERIGHT, animArray("resources/player/MOVERIGHT", 6));
        animation.addSequence(State.FALLLEFT, animArray("resources/player/FALLLEFT", 1));
        animation.addSequence(State.FALLRIGHT, animArray("resources/player/FALLRIGHT", 1));
    }

    public void jump() {
        boolean ground = iterateThroughCollision("down", 0) == 0;
        if (ground || secondJump) { //if touching ground
            if (ground) {
                velocity.vertical = jumpFactor;
                secondJump = true;
            } else if (secondJump) {
                velocity.vertical = jumpFactor;
                secondJump = false;
            }
            if (velocity.horizontal < 0) {
                state = State.FALLLEFT;
            } else if (velocity.horizontal > 0) {
                state = State.FALLRIGHT;
            } else {
                state = (state == State.FACELEFT) ? State.FALLLEFT : State.FALLRIGHT;
            }
        }
    }

    public void attack() {
        Attack attack;
        if (left) {
            attack = new Attack((int) rectangle.getX() - ATTACK_SPACE, (int) rectangle.getY());
        } else {
            attack = new Attack((int) rectangle.getX() + (int) rectangle.getWidth() +
                ATTACK_SPACE, (int) rectangle.getY());
        }
        for (CollisionUnit item : units) {
            if (attack.checkOverlap(item, "left", 0) != -1) {
                if (item instanceof Character && !(item instanceof Player)) {
                    ((Character) item).damageHealth(1);
                }
            }
        }
        game.addUnit(attack);
        //delay
        game.removeUnit(attack);
    }

    @Override
    public void update() {
        super.update();
    }
}
